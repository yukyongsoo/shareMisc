package com.yuk.sheremisc.account.outbound

import com.yuk.sheremisc.account.domain.Account
import com.yuk.sheremisc.account.domain.AccountId
import com.yuk.sheremisc.account.domain.AccountRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.math.BigInteger

@Repository
class DummyAccountRepository : AccountRepository {
    override fun new(account: Account): Mono<Account> {
        val createdAccount = Account(
            AccountId(BigInteger.ONE),
            account.virtualAccountNumber
        )

        return Mono.just(createdAccount)
    }
}
