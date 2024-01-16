package com.jakedelivery.db.user

import com.jakedelivery.db._common.constant.UserStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {
    // select * from user where id = ? and status = ? order by id desc limit 1
    fun findFirstByIdAndStatusOrderByIdDesc(userId: Long?, status: UserStatus?): UserEntity?

    // select * from user where email = ? and password = ? and status = ? order by id desc limit 1
    fun findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
        email: String?,
        password: String?,
        status: UserStatus?
    ): UserEntity?
}