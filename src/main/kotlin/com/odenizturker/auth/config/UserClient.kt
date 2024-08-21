package com.odenizturker.auth.config

import com.odenizturker.auth.model.user.UserCreationRequest
import com.odenizturker.auth.model.user.UserModel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class UserClient(
    @Value("\${locky.services.user.url}")
    private val url: String,
    @Qualifier("userRestClient")
    private val restClient: RestClient,
) {
    fun getByUsername(username: String): UserModel =
        restClient
            .get()
            .uri(url) { uriBuilder ->
                uriBuilder
                    .path("/users")
                    .queryParam("username", username)
                    .build()
            }.retrieve()
            .body(UserModel::class.java)!!

    fun create(userCreationRequest: UserCreationRequest): ResponseEntity<Void> =
        restClient
            .post()
            .uri("$url/users")
            .body(userCreationRequest)
            .retrieve()
            .toBodilessEntity()
}
