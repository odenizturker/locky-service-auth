package com.odenizturker.auth.service

import com.odenizturker.auth.config.UserClient
import com.odenizturker.auth.model.user.UserCreationRequest
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userClient: UserClient,
    private val passwordEncoder: PasswordEncoder,
) : UserDetailsService {
    fun create(user: UserCreationRequest) {
        userClient.create(
            UserCreationRequest(
                username = user.username,
                password = passwordEncoder.encode(user.password),
            ),
        )
    }

    override fun loadUserByUsername(username: String): UserDetails = userClient.getByUsername(username)
}
