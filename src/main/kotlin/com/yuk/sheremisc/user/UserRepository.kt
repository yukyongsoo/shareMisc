package com.yuk.sheremisc.user

import com.yuk.sheremisc.user.domain.OAuthUserId
import com.yuk.sheremisc.user.domain.User
import reactor.core.publisher.Mono

interface UserRepository {
    fun new(user: User): Mono<User>
    fun findByOAuthId(userId: OAuthUserId): Mono<User>
}
