package com.yuk.sheremisc.user

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.JWSObject
import com.nimbusds.jose.Payload
import com.nimbusds.jose.crypto.MACSigner
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.time.Instant

@Component
class JwtService : ReactiveJwtDecoder, Converter<Jwt, Mono<out AbstractAuthenticationToken>> {
    companion object {
        private const val key = "DGEWIPWAB19DREGKD8F1EIDKENTDIRE3"
    }
    fun encode(authorizedClient: OAuth2AuthorizedClient): Mono<String> {
        val jwsObject = JWSObject(
            JWSHeader(JWSAlgorithm.HS256),
            Payload(
                mapOf(
                    "iss" to authorizedClient.clientRegistration.registrationId,
                    "sub" to authorizedClient.principalName,
                    "access_token" to authorizedClient.accessToken.tokenValue,
                    "iat" to authorizedClient.accessToken.issuedAt!!.epochSecond,
                    "exp" to authorizedClient.accessToken.expiresAt!!.epochSecond
                )
            )
        )

        jwsObject.sign(MACSigner(key))

        return Mono.just(jwsObject.serialize())
    }

    override fun decode(token: String): Mono<Jwt> {
        val jwsObject = JWSObject.parse(token)
        val claims = jwsObject.payload.toJSONObject()

        return Mono.just(
            Jwt(
                "test",
                Instant.ofEpochMilli(claims["iat"]!!.toString().toLong()),
                Instant.ofEpochMilli(claims["exp"]!!.toString().toLong()),
                mapOf(
                    "alg" to jwsObject.header.algorithm.name
                ),
                claims
            )
        )
    }

    override fun convert(jwt: Jwt): Mono<out AbstractAuthenticationToken> {
        val sub = jwt.getClaim<String>("sub")

        val token = OAuth2AuthenticationToken(
            DefaultOAuth2User(
                listOf(
                    OAuth2UserAuthority("OAUTH2_USER", mapOf("sub" to sub)),
                    SimpleGrantedAuthority("SCOPE_openid")
                ),
                mapOf(
                    "sub" to sub
                ),
                "sub"
            ),
            listOf(
                OAuth2UserAuthority("OAUTH2_USER", mapOf("sub" to sub)),
                SimpleGrantedAuthority("SCOPE_openid")
            ),
            jwt.getClaim("iss")
        )

        return Mono.just(token)
    }
}
