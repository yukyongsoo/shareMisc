package com.yuk.sheremisc.product

data class Price(
    val value: Long
) {
    init {
        if (value < 0) {
            throw IllegalArgumentException("가격은 1보다 작을 수 없습니다")
        }
    }
}
