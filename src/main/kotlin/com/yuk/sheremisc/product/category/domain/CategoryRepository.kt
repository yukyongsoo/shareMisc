package com.yuk.sheremisc.product.category.domain

interface CategoryRepository {
    fun find(categoryId: CategoryId): Category?
    fun findChild(categoryId: CategoryId): Collection<Category>
}
