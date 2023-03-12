package com.yuk.sheremisc.order

import com.yuk.sheremisc.product.Price
import com.yuk.sheremisc.product.ProductId
import com.yuk.sheremisc.user.domain.UserId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigInteger

@Document
class Order(
    val sellerId: UserId,
    val buyerId: UserId,
    val productId: ProductId,
    val price: Price
) {
    @Id
    protected lateinit var _id: BigInteger

    val id
        get() = OrderId(_id)
}

@JvmInline
value class OrderId(val value: BigInteger)