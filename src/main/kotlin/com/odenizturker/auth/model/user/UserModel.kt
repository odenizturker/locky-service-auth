package com.odenizturker.auth.model.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

data class UserModel(
    val id: UUID,
    private val username: String,
    private val password: String,
    private val authorities: Set<Authorities>,
    val accountExpired: Boolean = false,
    val accountLocked: Boolean = false,
    val credentialsExpired: Boolean = false,
    val enabled: Boolean = true,
) : UserDetails {
    override fun getAuthorities(): List<GrantedAuthority> = authorities.toList()

    override fun getPassword(): String = password

    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = !accountExpired

    override fun isAccountNonLocked(): Boolean = !accountLocked

    override fun isCredentialsNonExpired(): Boolean = !credentialsExpired
}

enum class Authorities : GrantedAuthority {
    USER,
    ADMIN,
    ;

    override fun getAuthority(): String = this.name
}
