package com.jakedelivery.db.storemenu

import com.jakedelivery.db._common.constant.StoreMenuStatus
import org.springframework.data.jpa.repository.JpaRepository

interface StoreMenuRepository : JpaRepository<StoreMenuEntity, Long> {
    // 유효한 메뉴 체크
    // select * from store_menu where id = ? and status = ? order by id limit 1
    fun findFirstByIdAndStatusOrderByIdDesc(id: Long?, status: StoreMenuStatus?): StoreMenuEntity?

    // 특정 가게 메뉴 가져오기
    // select * from store_menu where store_id = ? and status = ? order by sequence desc;
    fun findAllByStoreIdAndStatusOrderBySequenceDesc(storeId: Long?, status: StoreMenuStatus?): List<StoreMenuEntity>
}