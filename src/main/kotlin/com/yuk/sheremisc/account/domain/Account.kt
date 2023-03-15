package com.yuk.sheremisc.account.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.query.not
import java.math.BigInteger

@Document
class Account(
    virtualAccountNumber: VirtualAccountNumber
) {
    constructor(
        accountId: AccountId,
        virtualAccountNumber: VirtualAccountNumber
    ) : this(virtualAccountNumber) {
        this._id = accountId.value
    }

    @Id
    protected lateinit var _id: BigInteger

    val id
        get() = AccountId(_id)

    @Field("balance")
    protected var _balance = 0L

    val balance: Balance
        get() = Balance(_balance)

    @Field("virtualAccountNumber")
    protected var _virtualAccountNumber = virtualAccountNumber.value

    val virtualAccountNumber
        get() = VirtualAccountNumber(_virtualAccountNumber)
}

@JvmInline
value class AccountId(val value: BigInteger)

@JvmInline
value class Balance(val value: Long)

@JvmInline
value class VirtualAccountNumber(val value: String)
