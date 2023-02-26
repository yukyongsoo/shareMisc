package com.yuk.sheremisc.user.inbound

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)
