package com.odenizturker.auth.config

import com.odenizturker.auth.model.client.ClientCreationRequest
import com.odenizturker.auth.model.client.ClientModel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import java.util.UUID

@Service
class ClientClient(
    @Value("\${locky.services.client.url}")
    private val url: String,
    @Qualifier("clientRestClient")
    private val restClient: RestClient,
) {
    fun getById(id: UUID): ClientModel =
        restClient
            .get()
            .uri("$url/clients/{id}", id)
            .retrieve()
            .body(ClientModel::class.java)!!

    fun getByClientId(clientId: String): ClientModel =
        restClient
            .get()
            .uri { uriBuilder ->
                uriBuilder
                    .path("$url/clients")
                    .queryParam("clientId", clientId)
                    .build()
            }.retrieve()
            .body(ClientModel::class.java)!!

    fun create(clientCreationRequest: ClientCreationRequest): ClientModel =
        restClient
            .post()
            .uri("$url/clients")
            .body(clientCreationRequest)
            .retrieve()
            .body(ClientModel::class.java)!!
}
