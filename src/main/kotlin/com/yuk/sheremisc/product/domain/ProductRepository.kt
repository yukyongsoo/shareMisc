package com.yuk.sheremisc.product.domain

import com.yuk.sheremisc.common.Page
import com.yuk.sheremisc.common.PageResponse
import reactor.core.publisher.Mono

interface ProductRepository {
    fun find(id: ProductId): Mono<Product>
    fun getPage(page: Page): Mono<PageResponse<Product>>
    fun new(product: Product): Mono<Product>
}
