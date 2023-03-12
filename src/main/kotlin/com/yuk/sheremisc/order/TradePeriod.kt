package com.yuk.sheremisc.order

import java.time.LocalDateTime

data class TradePeriod(
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime
)
