package com.yuk.sheremisc.account

import com.yuk.sheremisc.account.domain.Account
import com.yuk.sheremisc.account.domain.AccountId
import com.yuk.sheremisc.account.domain.AccountRepository
import com.yuk.sheremisc.account.domain.VirtualAccountNumber
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AccountService(
    private val accountRepository: AccountRepository
) {
    fun newAndGetId(): AccountId {
        val account = Account(createVirtualAccountNumber())

        val createAccount = accountRepository.new(account)

        return createAccount.id
    }

    private fun createVirtualAccountNumber(): VirtualAccountNumber {
        return VirtualAccountNumber(UUID.randomUUID().toString())
    }
}
