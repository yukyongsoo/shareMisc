package com.yuk.sheremisc.product.category.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document
class Category(
    @Field("category")
    val category: CategoryId,
    @Field("parent")
    val parent: CategoryId,
    @Field("name")
    val name: String
) {
    init {
        if (category.value.length <= parent.value.length) {
            throw IllegalArgumentException()
        }
    }

    @Id
    protected var id = ObjectId.get()
}

@JvmInline
value class CategoryId(val value: String) {
    init {
        if (value.length % 3 != 0) {
            throw IllegalArgumentException()
        }
    }
}
