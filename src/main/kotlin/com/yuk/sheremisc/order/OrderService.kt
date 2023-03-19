package com.yuk.sheremisc.order

import com.yuk.sheremisc.order.domain.Order
import com.yuk.sheremisc.order.domain.OrderRepository
import com.yuk.sheremisc.order.domain.TradePeriod
import com.yuk.sheremisc.order.domain.TradeTerms
import com.yuk.sheremisc.product.ProductService
import com.yuk.sheremisc.product.domain.Product
import com.yuk.sheremisc.product.domain.ProductId
import com.yuk.sheremisc.user.domain.UserId
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val productService: ProductService
) {
    fun new(productId: ProductId, buyerId: UserId, tradePeriod: TradePeriod): Mono<Order> {
        return productService.find(productId)
            .switchIfEmpty(Mono.error(IllegalArgumentException()))
            .map {
                calculatePrice(it, buyerId, tradePeriod)
            }
            .flatMap(orderRepository::new)
    }

    private fun calculatePrice(
        product: Product,
        buyerId: UserId,
        tradePeriod: TradePeriod
    ): Order {
        return Order(
            product.seller,
            buyerId,
            product.id,
            TradeTerms(product.price, tradePeriod)
        )
    }
}
