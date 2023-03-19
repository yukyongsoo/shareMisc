package com.yuk.sheremisc.order.outbound

import com.yuk.sheremisc.order.domain.Order
import com.yuk.sheremisc.order.domain.OrderRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class DummyOrderRepository : OrderRepository {
    override fun new(order: Order): Mono<Order> {
        TODO("Not yet implemented")
    }
}
