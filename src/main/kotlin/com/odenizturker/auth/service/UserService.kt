package com.odenizturker.auth.service

import com.odenizturker.auth.config.UserClient
import com.odenizturker.auth.model.user.UserCreationRequest
import com.odenizturker.auth.model.user.UserModel
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

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

    override fun loadUserByUsername(username: String): UserDetails =
        UserModel(
            id = UUID.randomUUID(),
            username = "username",
            password = passwordEncoder.encode("password"),
            authorities = setOf(),
            accountExpired = false,
            accountLocked = false,
            credentialsExpired = false,
            enabled = true,
        )
}
