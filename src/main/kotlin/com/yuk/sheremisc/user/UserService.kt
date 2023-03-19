package com.yuk.sheremisc.user

import com.yuk.sheremisc.account.AccountService
import com.yuk.sheremisc.user.domain.OAuthUserId
import com.yuk.sheremisc.user.domain.User
import com.yuk.sheremisc.user.domain.UserRepository
import com.yuk.sheremisc.user.domain.UserType
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.security.Principal

@Service
class UserService(
    private val userRepository: UserRepository,
    private val oAuthService: OAuthService,
    private val accountService: AccountService
) {
    fun login(principal: Mono<Principal>): Mono<String> {
        return oAuthService.getUserId(principal)
            .doOnNext(::saveNotExistUser)
            .then(oAuthService.getJwtToken(principal))
    }

    fun getMe(principal: Mono<Principal>): Mono<User> {
        return oAuthService.getUserId(principal)
            .flatMap(userRepository::findByOAuthId)
            .switchIfEmpty(Mono.error(IllegalArgumentException()))
    }

    private fun saveNotExistUser(userId: OAuthUserId): Mono<User> {
        return userRepository.findByOAuthId(userId)
            .switchIfEmpty(saveAndCreateAccount(userId))
    }

    private fun saveAndCreateAccount(userId: OAuthUserId): Mono<User> {
        return accountService.newAndGetId()
            .flatMap { accountId ->
                userRepository.new(User(userId, accountId, UserType.KAKAO))
            }
    }
}
