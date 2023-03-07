package com.yuk.sheremisc.user.inbound

import com.yuk.sheremisc.user.domain.User
import reactor.core.publisher.Mono

object UserResponseConvertor {
    fun convert(user: Mono<User>): Mono<UserResponse> {
        return user.map {
            UserResponse(
                it.id.value,
                it.oAuthUserId.value,
                it.type
            )
        }
    }
}
