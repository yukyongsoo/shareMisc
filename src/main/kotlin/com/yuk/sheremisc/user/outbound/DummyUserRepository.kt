package com.yuk.sheremisc.user.outbound

import com.yuk.sheremisc.user.domain.OAuthUserId
import com.yuk.sheremisc.user.domain.User
import com.yuk.sheremisc.user.domain.UserRepository
import com.yuk.sheremisc.user.domain.UserType
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class DummyUserRepository : UserRepository {

    override fun new(user: User): Mono<User> {
        return Mono.just(User(OAuthUserId("111"), UserType.KAKAO))
    }

    override fun findByOAuthId(userId: OAuthUserId): Mono<User> {
        return Mono.just(User(OAuthUserId("111"), UserType.KAKAO))
    }
}
