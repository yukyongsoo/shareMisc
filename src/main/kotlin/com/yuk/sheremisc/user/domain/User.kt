package com.yuk.sheremisc.user.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.math.BigInteger

@Document
class User(
    oAuthUserId: OAuthUserId,
    val type: UserType
) {
    val id: UserId
        get() = UserId(_id)

    @Id
    protected lateinit var _id: BigInteger

    val oAuthUserId: OAuthUserId
        get() = OAuthUserId(_oAuthUserId)

    @Field("oAuthUserId")
    protected val _oAuthUserId: String = oAuthUserId.value
}

@JvmInline
value class UserId(val value: BigInteger)

@JvmInline
value class OAuthUserId(val value: String)
