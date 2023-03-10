package com.yuk.sheremisc.product

import com.yuk.sheremisc.product.category.Category
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigInteger

@Document
class Product(
    val category: Category,
    val title: Title,
    val content: Content,
    val price: Price
) {
    @Id
    protected lateinit var _id: BigInteger

    val productId
        get() = ProductId(_id)
}

@JvmInline
value class ProductId(val value: BigInteger)

@JvmInline
value class Title(val value: String)

@JvmInline
value class Content(val value: String)
