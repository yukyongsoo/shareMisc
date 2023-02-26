package com.yuk.sheremisc.user

import com.yuk.sheremisc.user.inbound.TokenResponse
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import reactor.kotlin.extra.bool.not
import java.security.Principal

@Service
class UserService(
    private val userRepository: UserRepository,
    private val oAuthService: OAuthService
) {
    fun login(principal: Mono<Principal>): Mono<TokenResponse> {
        return oAuthService.getUserId(principal)
            .doOnNext(::saveNotExistUser)
            .then(oAuthService.getToken(principal))
    }

    private fun saveNotExistUser(userId: OAuthUserId): Mono<User> {
        return userRepository.findByOAuthId(userId)
            .switchIfEmpty(userRepository.new(User(userId, UserType.KAKAO)))
    }
}
