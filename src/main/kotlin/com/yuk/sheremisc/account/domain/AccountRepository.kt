package com.yuk.sheremisc.account.domain

import reactor.core.publisher.Mono

interface AccountRepository {
    fun new(account: Account): Mono<Account>
}
