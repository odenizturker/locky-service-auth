package com.odenizturker.auth.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.odenizturker.auth.config.ClientClient
import com.odenizturker.auth.model.client.ClientCreationRequest
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.core.oidc.OidcScopes
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ClientService(
    private val objectMapper: ObjectMapper,
    private val clientClient: ClientClient,
) : RegisteredClientRepository {
    val registeredClient =
        RegisteredClient
            .withId(UUID.randomUUID().toString())
            .clientId("default-client")
            .clientSecret("{noop}secret")
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .redirectUri("http://localhost:8080/login/oauth2/code/default-client")
            .postLogoutRedirectUri("http://localhost:8080/")
            .scope(OidcScopes.OPENID)
            .scope(OidcScopes.PROFILE)
            .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
            .build()

    final override fun save(registeredClient: RegisteredClient) {
        clientClient.create(ClientCreationRequest(registeredClient))
    }

    override fun findById(id: String): RegisteredClient = registeredClient

    override fun findByClientId(clientId: String): RegisteredClient = registeredClient
}
