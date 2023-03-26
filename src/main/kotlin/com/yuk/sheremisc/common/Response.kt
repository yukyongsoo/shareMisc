package com.yuk.sheremisc.common

open class CommonResponse<T>(
    val data: T
)

data class Page(
    val page: Long,
    val size: Long
)

class PageResponse<T>(
    data: Collection<T>,
    val page: Page
) : CommonResponse<Collection<T>>(data)

class ErrorResponse(
    code: String,
    reason: String
)
