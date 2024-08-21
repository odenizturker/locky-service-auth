package com.odenizturker.auth.model.client

import org.springframework.security.oauth2.jose.jws.JwsAlgorithm
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings as CS

data class ClientSettings(
    val requireProofKey: Boolean?,
    val requireAuthorizationConsent: Boolean?,
    val jwkSetUrl: String?,
    val tokenEndpointAuthenticationSigningAlgorithm: JwsAlgorithm?,
    val x509CertificateSubjectDN: String?,
) {
    constructor(client: CS) : this(
        requireProofKey = client.isRequireProofKey,
        requireAuthorizationConsent = client.isRequireAuthorizationConsent,
        jwkSetUrl = client.jwkSetUrl,
        tokenEndpointAuthenticationSigningAlgorithm = client.tokenEndpointAuthenticationSigningAlgorithm,
        x509CertificateSubjectDN = client.x509CertificateSubjectDN,
    )

    fun toClientSettings(): CS {
        val builder = CS.builder()

        requireProofKey?.let { builder.requireProofKey(it) }
        requireAuthorizationConsent?.let { builder.requireAuthorizationConsent(it) }
        jwkSetUrl?.let { builder.jwkSetUrl(it) }
        tokenEndpointAuthenticationSigningAlgorithm?.let { builder.tokenEndpointAuthenticationSigningAlgorithm(it) }
        x509CertificateSubjectDN?.let { builder.x509CertificateSubjectDN(it) }

        return builder.build()
    }
}
