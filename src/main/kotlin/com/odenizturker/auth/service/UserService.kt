package com.odenizturker.auth.service

import com.odenizturker.auth.config.UserRestClient
import com.odenizturker.auth.model.user.UserCreationRequest
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRestClient: UserRestClient,
    private val passwordEncoder: PasswordEncoder,
) : UserDetailsService {
    fun create(user: UserCreationRequest) {
        userRestClient.create(
            UserCreationRequest(
                username = user.username,
                password = passwordEncoder.encode(user.password),
            ),
        )
    }

    override fun loadUserByUsername(username: String): UserDetails = userRestClient.getByUsername(username)
}
