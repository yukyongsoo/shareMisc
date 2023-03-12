package com.yuk.sheremisc.order

import com.yuk.sheremisc.product.Price

data class TradeTerms(
    val price: Price,
    val period: TradePeriod
)
