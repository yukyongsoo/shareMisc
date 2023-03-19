package com.yuk.sheremisc.order

import com.yuk.sheremisc.product.domain.Price

data class TradeTerms(
    val price: Price,
    val period: TradePeriod
)
