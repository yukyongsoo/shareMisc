package com.yuk.sheremisc.product.domain

import com.yuk.sheremisc.product.category.domain.CategoryId
import com.yuk.sheremisc.user.domain.UserId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigInteger

@Document
class Product(
    val category: CategoryId,
    val title: Title,
    val content: Content,
    val price: Price,
    val seller: UserId
) {
    @Id
    protected lateinit var _id: BigInteger

    val id
        get() = ProductId(_id)
}

@JvmInline
value class ProductId(val value: BigInteger) {
    init {
        if (value == BigInteger.ZERO) {
            throw IllegalArgumentException()
        }
    }
}

@JvmInline
value class Title(val value: String) {
    init {
        if (value.isBlank()) {
            throw IllegalArgumentException()
        }
    }
}

@JvmInline
value class Content(val value: String) {
    init {
        if (value.isBlank()) {
            throw IllegalArgumentException()
        }
    }
}
