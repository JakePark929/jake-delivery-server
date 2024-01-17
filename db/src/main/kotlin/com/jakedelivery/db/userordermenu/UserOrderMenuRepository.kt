package com.jakedelivery.db.userordermenu

import com.jakedelivery.db._common.constant.UserOrderMenuStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserOrderMenuRepository : JpaRepository<UserOrderMenuEntity, Long> {
    // select * from user_order_menu where user_order_id = ? status = ?
    fun findAllByUserOrderIdAndStatus(userOrderId: Long?, status: UserOrderMenuStatus?): List<UserOrderMenuEntity>
}