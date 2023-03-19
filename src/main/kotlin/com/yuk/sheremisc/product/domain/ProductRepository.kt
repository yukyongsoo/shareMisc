package com.yuk.sheremisc.product.domain

import reactor.core.publisher.Mono

interface ProductRepository {
    fun find(id: ProductId): Mono<Product>
}
