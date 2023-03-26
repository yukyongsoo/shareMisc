package com.yuk.sheremisc.product.outbound

import com.yuk.sheremisc.common.Page
import com.yuk.sheremisc.common.PageResponse
import com.yuk.sheremisc.product.category.Category
import com.yuk.sheremisc.product.domain.Content
import com.yuk.sheremisc.product.domain.Price
import com.yuk.sheremisc.product.domain.Product
import com.yuk.sheremisc.product.domain.ProductId
import com.yuk.sheremisc.product.domain.ProductRepository
import com.yuk.sheremisc.product.domain.Title
import com.yuk.sheremisc.user.domain.UserId
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.math.BigInteger

@Repository
class DummyProductRepository : ProductRepository {
    override fun find(id: ProductId): Mono<Product> {
        val product = Product(
            Category("123123123"),
            Title("title"),
            Content("content"),
            Price(1000),
            UserId(BigInteger.ONE)
        )

        return Mono.just(product)
    }

    override fun getPage(page: Page): Mono<PageResponse<Product>> {
        val page = PageResponse(
            listOf(
                Product(
                    Category("123123123"),
                    Title("title"),
                    Content("content"),
                    Price(1000),
                    UserId(BigInteger.ONE)
                )
            ),
            page
        )

        return Mono.just(page)
    }
}
