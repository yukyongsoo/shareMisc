package com.yuk.sheremisc.account.outbound

import com.yuk.sheremisc.account.domain.Account
import com.yuk.sheremisc.account.domain.AccountId
import com.yuk.sheremisc.account.domain.AccountRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
class DummyAccountRepository : AccountRepository {
    override fun new(account: Account): Account {
        return Account(
            AccountId(BigInteger.ONE),
            account.virtualAccountNumber
        )
    }
}
