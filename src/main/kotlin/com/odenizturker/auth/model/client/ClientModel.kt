package com.odenizturker.auth.model.client

import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import java.time.Instant
import java.util.UUID

data class ClientModel(
    val id: UUID,
    val clientId: String,
    val clientIdIssuedAt: Instant?,
    val clientSecret: String?,
    val clientSecretExpiresAt: Instant?,
    val clientName: String,
    val clientAuthenticationMethods: Set<AuthenticationMethod>,
    val authorizationGrantTypes: Set<GrantType>,
    val redirectUris: Set<String>,
    val postLogoutRedirectUris: Set<String>,
    val scopes: Set<String>,
    val clientSettings: ClientSettings,
    val tokenSettings: TokenSettings,
) {
    fun toRegisteredClient(): RegisteredClient =
        RegisteredClient
            .withId(id.toString())
            .clientId(clientId)
            .clientSecret(clientSecret)
            .clientAuthenticationMethods {
                it.addAll(clientAuthenticationMethods.map { ClientAuthenticationMethod(it.value) })
            }.authorizationGrantTypes {
                it.addAll(authorizationGrantTypes.map { AuthorizationGrantType(it.value) })
            }.redirectUris { it.addAll(redirectUris) }
            .postLogoutRedirectUris { it.addAll(postLogoutRedirectUris) }
            .scopes { it.addAll(scopes) }
            .clientSettings(clientSettings.toClientSettings())
            .tokenSettings(tokenSettings.toTokenSettings())
            .build()
}
