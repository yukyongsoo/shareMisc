package com.yuk.sheremisc.order.domain

import com.yuk.sheremisc.product.domain.ProductId
import com.yuk.sheremisc.user.domain.UserId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigInteger

@Document
class Order(
    val sellerId: UserId,
    val buyerId: UserId,
    val productId: ProductId,
    val tradeTerms: TradeTerms
) {
    @Id
    protected lateinit var _id: BigInteger

    val id
        get() = OrderId(_id)
}

@JvmInline
value class OrderId(val value: BigInteger)
