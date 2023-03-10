package com.yuk.sheremisc.user.domain

import reactor.core.publisher.Mono

interface UserRepository {
    fun new(user: User): Mono<User>
    fun findByOAuthId(userId: OAuthUserId): Mono<User>
}
