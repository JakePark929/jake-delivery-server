package com.jakedelivery.api.temp

import com.jakedelivery.db._common.constant.UserStatus
import com.jakedelivery.db.user.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/temp")
@RestController
class TempApiController(
    val userRepository: UserRepository
) {
    @GetMapping("")
    fun temp(): String {
        val user = userRepository.findFirstByIdAndStatusOrderByIdDesc(10, UserStatus.REGISTERED)
        return "hello kotlin spring boot"
    }
}