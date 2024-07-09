package com.fiap.fastfood.core.application.usecase

import com.fiap.fastfood.core.dto.ProductDTO
import com.fiap.fastfood.core.valueObject.ProductCategory

interface ProductUseCase {

    fun create(product: ProductDTO)

    fun findByCategory(category: ProductCategory): List<ProductDTO>

    fun update(id: Long, product: ProductDTO)

    fun delete(id: Long)
}