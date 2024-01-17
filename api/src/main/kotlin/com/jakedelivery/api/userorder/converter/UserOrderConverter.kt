package com.jakedelivery.api.userorder.converter

import com.jakedelivery.api.user.model.User
import com.jakedelivery.api.userorder.dto.UserOrderResponse
import com.jakedelivery.common.annotation.Converter
import com.jakedelivery.db.store.StoreEntity
import com.jakedelivery.db.storemenu.StoreMenuEntity
import com.jakedelivery.db.userorder.UserOrderEntity

@Converter
class UserOrderConverter {
    fun toEntity(
        user: User?,
        storeEntity: StoreEntity?,
        storeMenuEntities: List<StoreMenuEntity>?
    ): UserOrderEntity {
        val totalAmount = storeMenuEntities
            ?.mapNotNull { it.amount }
            ?.reduce { acc, bigDecimal -> acc.add(bigDecimal) }

        return UserOrderEntity(
            userId = user?.id,
            store = storeEntity,
            amount = totalAmount,
        )
    }

    fun toResponse(
        userOrderEntity: UserOrderEntity?
    ): UserOrderResponse {
        return UserOrderResponse(
            id = userOrderEntity?.id,
            status = userOrderEntity?.status,
            amount = userOrderEntity?.amount,
            orderedAt = userOrderEntity?.orderedAt,
            acceptedAt = userOrderEntity?.acceptedAt,
            cookingStartedAt = userOrderEntity?.cookingStartedAt,
            deliveryStartedAt = userOrderEntity?.deliveryStartedAt,
            receivedAt = userOrderEntity?.receivedAt
        )
    }
}