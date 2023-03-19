package com.yuk.sheremisc.order.domain

import com.yuk.sheremisc.product.domain.Price

data class TradeTerms(
    val price: Price,
    val period: TradePeriod
)
