package com.yuk.sheremisc.account

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.math.BigInteger

@Document
class Account(
    virtualAccountNumber: VirtualAccountNumber
) {
    @Id
    protected lateinit var _id: BigInteger

    val id: AccountId
        get() = AccountId(_id)

    @Field("balance")
    protected var _balance = 0L

    val balance: Balance
        get() = Balance(_balance)

    @Field("virtualAccountNumber")
    protected var _virtualAccountNumber = virtualAccountNumber.value

    val virtualAccountNumber: VirtualAccountNumber
        get() = VirtualAccountNumber(_virtualAccountNumber)
}

@JvmInline
value class AccountId(val value: BigInteger)

@JvmInline
value class Balance(val value: Long)

@JvmInline
value class VirtualAccountNumber(val value: String)
