package com.odenizturker.auth.config

import com.odenizturker.auth.model.user.UserCreationRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class UserRestClient(
    @Value("\${locky.services.user.url}")
    private val url: String,
    private val restClient: RestClient,
) {
    fun getByUsername(username: String): UserDetails =
        restClient
            .get()
            .uri { uriBuilder ->
                uriBuilder
                    .path("$url/users")
                    .queryParam("username", username)
                    .build()
            }.retrieve()
            .body(UserDetails::class.java)!!

    fun create(userCreationRequest: UserCreationRequest): ResponseEntity<Void> =
        restClient
            .post()
            .uri("$url/users")
            .body(userCreationRequest)
            .retrieve()
            .toBodilessEntity()
}
