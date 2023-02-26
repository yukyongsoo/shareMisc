package com.yuk.sheremisc.user

class User(
    val oAuthUserId: OAuthUserId,
    val type: UserType
) {
    val id: UserId = UserId("0")
}

@JvmInline
value class UserId(val value: String)

@JvmInline
value class OAuthUserId(val value: String)
