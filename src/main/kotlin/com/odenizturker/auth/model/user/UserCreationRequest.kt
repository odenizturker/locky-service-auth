package com.odenizturker.auth.model.user

data class UserCreationRequest(
    val username: String,
    val password: String,
)
