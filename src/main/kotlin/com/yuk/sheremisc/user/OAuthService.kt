package com.yuk.sheremisc.user

import com.yuk.sheremisc.user.domain.OAuthUserId
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.cast
import java.security.Principal

@Service
class OAuthService(
    private val authorizedClientService: ReactiveOAuth2AuthorizedClientService,
    private val jwtService: JwtService
) {
    fun getJwtToken(authentication: Mono<Principal>): Mono<String> {
        return authentication.cast<OAuth2AuthenticationToken>()
            .onErrorComplete()
            .flatMap { token ->
                val clientRegistrationId = token.authorizedClientRegistrationId

                convertJwtToken(clientRegistrationId, token.name)
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

    private fun convertJwtToken(clientRegistrationId: String, principalName: String): Mono<String> {
        return authorizedClientService.loadAuthorizedClient<OAuth2AuthorizedClient>(clientRegistrationId, principalName)
            .flatMap(jwtService::encode)
    }
}
