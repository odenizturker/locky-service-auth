package com.odenizturker.auth.controller

import com.odenizturker.auth.model.user.UserCreationRequest
import com.odenizturker.auth.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val userService: UserService,
) {
    @PostMapping("/register")
    fun register(
        @RequestBody userCreationRequest: UserCreationRequest,
    ) = userService.create(userCreationRequest)
}
