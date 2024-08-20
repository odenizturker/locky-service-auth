package com.odenizturker.auth.config

import com.odenizturker.auth.model.client.ClientCreationRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import java.util.UUID

@Service
class ClientRestClient(
    @Value("\${locky.services.client.url}")
    private val url: String,
    private val restClient: RestClient,
) {
    fun getById(id: UUID): RegisteredClient =
        restClient
            .get()
            .uri("$url/clients/{id}", id)
            .retrieve()
            .body(RegisteredClient::class.java)!!

    fun getByClientId(clientId: String): RegisteredClient =
        restClient
            .get()
            .uri { uriBuilder ->
                uriBuilder
                    .path("$url/clients")
                    .queryParam("clientId", clientId)
                    .build()
            }.retrieve()
            .body(RegisteredClient::class.java)!!

    fun create(clientCreationRequest: ClientCreationRequest): ResponseEntity<Void> =
        restClient
            .post()
            .uri("$url/clients")
            .body(clientCreationRequest)
            .retrieve()
            .toBodilessEntity()
}
