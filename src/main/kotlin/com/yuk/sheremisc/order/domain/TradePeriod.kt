package com.yuk.sheremisc.order.domain

import java.time.LocalDateTime

data class TradePeriod(
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime
) {
    init {
        if (startDateTime >= endDateTime) {
            throw IllegalStateException()
        }
    }
}
