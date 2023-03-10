package com.yuk.sheremisc.user.inbound

import com.yuk.sheremisc.user.domain.UserType
import java.math.BigInteger

data class UserResponse(
    val id: BigInteger,
    val oAuthUserId: String,
    val type: UserType
)
