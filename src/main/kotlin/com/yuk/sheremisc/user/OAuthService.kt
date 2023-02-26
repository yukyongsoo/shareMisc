package com.yuk.sheremisc.user

import com.yuk.sheremisc.user.inbound.TokenResponse
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.cast
import java.security.Principal

@Service
class OAuthService(
    private val authorizedClientService: ReactiveOAuth2AuthorizedClientService
) {
    fun getToken(authentication: Mono<Principal>): Mono<TokenResponse> {
        val tokenMono = authentication.cast<OAuth2AuthenticationToken>()
            .onErrorComplete()

        return authentication.zipWith(tokenMono)
            .flatMap { tuple ->
                val principal = tuple.t1
                val token = tuple.t2
                val clientRegistrationId = token.authorizedClientRegistrationId

                extractToken(clientRegistrationId, principal.name)
            }
    }

    fun getUserId(authentication: Mono<Principal>): Mono<OAuthUserId> {
        return authentication.cast<OAuth2AuthenticationToken>()
            .onErrorComplete()
            .map {
                val id = it.principal.getAttribute<String>("sub")!!

                OAuthUserId(id)
            }
    }

    private fun extractToken(clientRegistrationId: String, principalName: String): Mono<TokenResponse> {
        return authorizedClientService.loadAuthorizedClient<OAuth2AuthorizedClient>(clientRegistrationId, principalName)
            .map { authorizedClient ->
                val accessToken = authorizedClient.accessToken.tokenValue
                val refreshToken = authorizedClient.refreshToken!!.tokenValue

                TokenResponse(accessToken, refreshToken)
            }
    }
}
