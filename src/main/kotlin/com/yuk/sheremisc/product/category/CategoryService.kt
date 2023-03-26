package com.yuk.sheremisc.product.category

import com.yuk.sheremisc.product.category.domain.Category
import com.yuk.sheremisc.product.category.domain.CategoryId
import com.yuk.sheremisc.product.category.domain.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    private val root = CategoryId("0")
    fun getCategory(categoryId: CategoryId): Category {
        return categoryRepository.find(categoryId)
            ?: throw IllegalArgumentException()
    }

    fun getChild(categoryId: CategoryId): Collection<Category> {
        return categoryRepository.findChild(categoryId)
    }

    fun getRoots(): Collection<Category> {
        return categoryRepository.findChild(root)
    }
}
