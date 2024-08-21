package com.odenizturker.auth.model.client

import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat
import java.time.Duration
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings as TS

data class TokenSettings(
    val reuseRefreshTokens: Boolean = false,
    val x509CertificateBoundAccessTokens: Boolean = false,
    val accessTokenFormat: OAuth2TokenFormat = OAuth2TokenFormat.SELF_CONTAINED,
    val accessTokenTimeToLive: Duration = Duration.ofMinutes(5),
    val authorizationCodeTimeToLive: Duration = Duration.ofMinutes(1),
    val deviceCodeTimeToLive: Duration = Duration.ofMinutes(1),
    val idTokenSignatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.RS256,
    val refreshTokenTimeToLive: Duration = Duration.ofMinutes(30),
) {
    constructor(token: TS) : this(
        reuseRefreshTokens = token.isReuseRefreshTokens,
        x509CertificateBoundAccessTokens = token.isX509CertificateBoundAccessTokens,
        accessTokenFormat = token.accessTokenFormat,
        accessTokenTimeToLive = token.accessTokenTimeToLive,
        authorizationCodeTimeToLive = token.authorizationCodeTimeToLive,
        deviceCodeTimeToLive = token.deviceCodeTimeToLive,
        idTokenSignatureAlgorithm = token.idTokenSignatureAlgorithm,
        refreshTokenTimeToLive = token.refreshTokenTimeToLive,
    )

    fun toTokenSettings(): TS =
        TS
            .builder()
            .accessTokenFormat(accessTokenFormat)
            .deviceCodeTimeToLive(deviceCodeTimeToLive)
            .accessTokenTimeToLive(accessTokenTimeToLive)
            .refreshTokenTimeToLive(refreshTokenTimeToLive)
            .authorizationCodeTimeToLive(authorizationCodeTimeToLive)
            .idTokenSignatureAlgorithm(idTokenSignatureAlgorithm)
            .reuseRefreshTokens(reuseRefreshTokens)
            .x509CertificateBoundAccessTokens(x509CertificateBoundAccessTokens)
            .build()
}
