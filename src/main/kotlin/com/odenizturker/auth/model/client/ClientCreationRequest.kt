package com.odenizturker.auth.model.client

import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import java.io.Serializable
import java.time.Instant

data class ClientCreationRequest(
    val clientId: String,
    val clientSecret: String,
    val clientSecretExpiresAt: Instant?,
    val clientName: String,
    val clientAuthenticationMethods: Set<AuthenticationMethod>,
    val authorizationGrantTypes: Set<GrantType>,
    val redirectUris: Set<String>,
    val postLogoutRedirectUris: Set<String>,
    val scopes: Set<String>,
    val clientSettings: ClientSettings,
    val tokenSettings: TokenSettings,
) : Serializable {
    constructor(client: RegisteredClient) : this(
        clientId = client.clientId,
        clientSecret = client.clientSecret ?: "",
        clientSecretExpiresAt = client.clientSecretExpiresAt,
        clientName = client.clientName,
        clientAuthenticationMethods = AuthenticationMethod.from(client.clientAuthenticationMethods),
        authorizationGrantTypes = GrantType.from(client.authorizationGrantTypes),
        redirectUris = client.redirectUris,
        postLogoutRedirectUris = client.postLogoutRedirectUris,
        scopes = client.scopes,
        clientSettings = ClientSettings(client.clientSettings),
        tokenSettings = TokenSettings(client.tokenSettings),
    )
}
