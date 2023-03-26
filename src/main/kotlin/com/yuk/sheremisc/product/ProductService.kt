package com.yuk.sheremisc.product

import com.yuk.sheremisc.common.Page
import com.yuk.sheremisc.common.PageResponse
import com.yuk.sheremisc.product.category.domain.CategoryId
import com.yuk.sheremisc.product.domain.Content
import com.yuk.sheremisc.product.domain.Price
import com.yuk.sheremisc.product.domain.Product
import com.yuk.sheremisc.product.domain.ProductId
import com.yuk.sheremisc.product.domain.ProductRepository
import com.yuk.sheremisc.product.domain.Title
import com.yuk.sheremisc.user.domain.UserId
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun find(id: ProductId): Mono<Product> {
        return productRepository.find(id)
    }

    fun getPage(page: Page): Mono<PageResponse<Product>> {
        return productRepository.getPage(page)
    }

    fun new(categoryId: CategoryId, title: Title, content: Content, price: Price, sellerUserId: UserId) {
        val product = Product(
            categoryId,
            title,
            content,
            price,
            sellerUserId
        )

        productRepository.new(product)
            .subscribe()
    }
}
