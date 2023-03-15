package com.yuk.sheremisc.account.domain

interface AccountRepository {
    fun new(account: WriteAccount): Account
}
