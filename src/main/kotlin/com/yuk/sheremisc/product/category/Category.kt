package com.yuk.sheremisc.product.category

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document
class Category(
    @Field("category")
    protected val category: String
) {
    init {
        if (category.trim().length != 9) {
            throw IllegalArgumentException("카테고리의 길이는 9여야 합니다")
        }
        if ("[0-9]+".toRegex().matches(category).not()) {
            throw IllegalArgumentException("카테고리는 숫자만 포함해야 합니다")
        }
    }

    @Transient
    val categoryL1 = CategoryL1(
        category.substring(0, 3)
    )

    @Transient
    val categoryL2 = CategoryL2(
        category.substring(3, 6)
    )

    @Transient
    val categoryL3 = CategoryL3(
        category.substring(6, 9)
    )

    @Id
    protected var id = ObjectId.get()
}

@JvmInline
value class CategoryL1(val value: String)

@JvmInline
value class CategoryL2(val value: String)

@JvmInline
value class CategoryL3(val value: String)
