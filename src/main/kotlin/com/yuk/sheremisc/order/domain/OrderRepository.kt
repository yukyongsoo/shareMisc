package com.yuk.sheremisc.order.domain

import reactor.core.publisher.Mono

interface OrderRepository {
    fun new(order: Order): Mono<Order>
}
