package com.jakedelivery.api.userorder.business

import com.jakedelivery.api._core.common.log.Log
import com.jakedelivery.api.store.converter.StoreConverter
import com.jakedelivery.api.store.service.StoreService
import com.jakedelivery.api.storemenu.converter.StoreMenuConverter
import com.jakedelivery.api.storemenu.service.StoreMenuService
import com.jakedelivery.api.user.model.User
import com.jakedelivery.api.userorder.converter.UserOrderConverter
import com.jakedelivery.api.userorder.dto.UserOrderDetailResponse
import com.jakedelivery.api.userorder.dto.UserOrderRequest
import com.jakedelivery.api.userorder.dto.UserOrderResponse
import com.jakedelivery.api.userorder.producer.UserOrderProducer
import com.jakedelivery.api.userorder.service.UserOrderService
import com.jakedelivery.api.userordermenu.converter.UserOrderMenuConverter
import com.jakedelivery.api.userordermenu.service.UserOrderMenuService
import com.jakedelivery.common.annotation.Business
import com.jakedelivery.db._common.constant.UserOrderMenuStatus
import kotlin.streams.toList

@Business
class UserOrderBusiness(
    private val userOrderService: UserOrderService,
    private val userOrderConverter: UserOrderConverter,

    private val storeService: StoreService,
    private val storeConverter: StoreConverter,

    private val storeMenuService: StoreMenuService,
    private val storeMenuConverter: StoreMenuConverter,

    private val userOrderMenuService: UserOrderMenuService,
    private val userOrderMenuConverter: UserOrderMenuConverter,

    private val userOrderProducer: UserOrderProducer
) {
    companion object : Log

    fun userOrder(user: User?, request: UserOrderRequest?): UserOrderResponse {
        // 가게 찾기
        val storeEntity = storeService.getStoreWithThrow(request?.storeId)

        // 주문한 메뉴 찾기
        val storeMenuEntities = request?.storeMenuIds
            ?.mapNotNull { storeMenuService.getStoreMenuWithThrow(it) }
            ?.toList()

        // 주문
        val userOrderEntity = userOrderConverter.toEntity(
            user = user,
            storeEntity = storeEntity,
            storeMenuEntities = storeMenuEntities
        ).run {
            userOrderService.order(this)
        }

        // 매핑 - user order menu list 생성
        val userOrderMenuEntities = storeMenuEntities
            ?.map { userOrderMenuConverter.toEntity(userOrderEntity, it) }
            ?.toList()

        // 주문 기록 남기기
        userOrderMenuEntities?.forEach { userOrderMenuService.order(it) }

        // 비동기로 주문 알리기
        userOrderProducer.sendOrder(userOrderEntity)

        return userOrderConverter.toResponse(userOrderEntity)
    }

    fun current(user: User?): List<UserOrderDetailResponse>? {
        return userOrderService.current(user?.id).map { userOrderEntity ->
            val storeMenuEntities = userOrderEntity.userOrderMenuEntities?.stream()
                ?.filter { userOrderMenuEntity -> userOrderMenuEntity.status == UserOrderMenuStatus.REGISTERED }
                ?.map { userOrderMenuEntity -> userOrderMenuEntity.storeMenu }
                ?.toList()

            UserOrderDetailResponse (
                userOrderResponse = userOrderConverter.toResponse(userOrderEntity),
                storeResponse = storeConverter.toResponse(userOrderEntity.store),
                storeMenuResponses = storeMenuConverter.toResponse(storeMenuEntities)
            )
        }.toList()
    }

    fun history(user: User?): List<UserOrderDetailResponse>? {
        return userOrderService.history(user?.id).map { userOrderEntity ->
            val storeMenuEntities = userOrderEntity.userOrderMenuEntities?.stream()
                ?.filter { userOrderMenuEntity -> userOrderMenuEntity.status == UserOrderMenuStatus.REGISTERED }
                ?.map { userOrderMenuEntity -> userOrderMenuEntity.storeMenu }
                ?.toList()

            UserOrderDetailResponse(
                userOrderResponse = userOrderConverter.toResponse(userOrderEntity),
                storeResponse = storeConverter.toResponse(userOrderEntity.store),
                storeMenuResponses = storeMenuConverter.toResponse(storeMenuEntities)
            )
        }.toList()
    }

    fun read(user: User?, orderId: Long): UserOrderDetailResponse {
        return userOrderService.getUserOrderWithOutStatusWithThrow(orderId, user?.id).let { userOrderEntity ->
            val storeMenuEntities = userOrderEntity.userOrderMenuEntities?.stream()
                ?.filter { it.status == UserOrderMenuStatus.REGISTERED }
                ?.map { it.storeMenu }
                ?.toList()
                ?: listOf()

            UserOrderDetailResponse(
                userOrderResponse = userOrderConverter.toResponse(userOrderEntity),
                storeResponse = storeConverter.toResponse(userOrderEntity.store),
                storeMenuResponses = storeMenuConverter.toResponse(storeMenuEntities)
            )
        }
    }
}