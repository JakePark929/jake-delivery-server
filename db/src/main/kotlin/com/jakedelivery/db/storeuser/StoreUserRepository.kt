package com.jakedelivery.db.storeuser

import com.jakedelivery.db._common.constant.StoreUserStatus
import org.springframework.data.jpa.repository.JpaRepository

interface StoreUserRepository : JpaRepository<StoreUserEntity, Long> {
    // select * from store_user where email = ? and status = ? order by id desc limit 1
    fun findFirstByEmailAndStatusOrderByIdDesc(email: String?, status: StoreUserStatus?): StoreUserEntity?
}