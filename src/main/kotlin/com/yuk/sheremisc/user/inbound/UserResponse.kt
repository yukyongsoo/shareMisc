package com.yuk.sheremisc.user.inbound

import com.yuk.sheremisc.user.domain.UserType

data class UserResponse(
    val id: String,
    val oAuthUserId: String,
    val type: UserType
)
