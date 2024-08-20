package com.odenizturker.auth.service

import com.odenizturker.auth.config.ClientRestClient
import com.odenizturker.auth.model.client.ClientCreationRequest
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ClientService(
    private val clientRestClient: ClientRestClient,
) : RegisteredClientRepository {
    final override fun save(registeredClient: RegisteredClient) {
        clientRestClient.create(ClientCreationRequest(registeredClient))
    }

    override fun findById(id: String): RegisteredClient = clientRestClient.getById(UUID.fromString(id))

    override fun findByClientId(clientId: String): RegisteredClient = clientRestClient.getByClientId(clientId)
}
