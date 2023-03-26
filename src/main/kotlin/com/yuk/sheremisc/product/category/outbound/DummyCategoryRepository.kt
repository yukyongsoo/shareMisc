package com.yuk.sheremisc.product.category.outbound

import com.yuk.sheremisc.product.category.domain.Category
import com.yuk.sheremisc.product.category.domain.CategoryId
import com.yuk.sheremisc.product.category.domain.CategoryRepository
import org.springframework.stereotype.Repository

@Repository
class DummyCategoryRepository : CategoryRepository {
    override fun find(categoryId: CategoryId): Category {
        return Category(
            CategoryId("100100100"),
            CategoryId("100100"),
            "Leaf"
        )
    }

    override fun findChild(categoryId: CategoryId): Collection<Category> {
        return listOf(
            Category(
                CategoryId("100100100"),
                CategoryId("100100"),
                "Leaf"
            )
        )
    }
}
