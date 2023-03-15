package com.yuk.sheremisc.user.domain

import com.yuk.sheremisc.account.domain.AccountId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.math.BigInteger

@Document
class User(
    oAuthUserId: OAuthUserId,
    accountId: AccountId,
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

    val accountId: AccountId
        get() = AccountId(_accountId)

    @Field("accountId")
    protected val _accountId: BigInteger = accountId.value
}

@JvmInline
value class UserId(val value: BigInteger)

@JvmInline
value class OAuthUserId(val value: String)
