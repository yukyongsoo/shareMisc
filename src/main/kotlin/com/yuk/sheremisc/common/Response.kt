package com.yuk.sheremisc.common

open class CommonResponse<T>(
    val data: T
)

data class Page(
    val page: Long,
    val size: Long
)

class PageResponse<T>(
    data: T,
    val page: Page
) : CommonResponse<T>(data)

class ErrorResponse(
    code: String,
    reason: String
)
