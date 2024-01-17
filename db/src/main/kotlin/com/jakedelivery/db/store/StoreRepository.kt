package com.jakedelivery.db.store

import com.jakedelivery.db._common.constant.StoreCategory
import com.jakedelivery.db._common.constant.StoreStatus
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository : JpaRepository<StoreEntity, Long> {
    // 유효한 스토어
    // select * from store where id = ? and status = ? order by id desc limit 1
    fun findFirstByIdAndStatusOrderByIdDesc(id: Long?, status: StoreStatus?): StoreEntity?

    // 유효한 스토어 리스트
    // select * from store where status = ? order by id desc
    fun findAllByStatusOrderByIdDesc(status: StoreStatus?): List<StoreEntity>

    // 유효한 특정 카테고리 스토어 리스트
    fun findAllByStatusAndCategoryOrderByStarDesc(
        storeStatus: StoreStatus?,
        storeCategory: StoreCategory?
    ): List<StoreEntity>

    // select * from store where name = ? and status = ? order by id desc limit 1
    fun findFirstByNameAndStatusOrderByIdDesc(name: String?, status: StoreStatus?): StoreEntity?
}