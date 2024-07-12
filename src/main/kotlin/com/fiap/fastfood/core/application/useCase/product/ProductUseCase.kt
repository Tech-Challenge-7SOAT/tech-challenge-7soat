package com.fiap.fastfood.core.application.useCase.product

import com.fiap.fastfood.core.dto.ProductDTO
import com.fiap.fastfood.core.entity.ProductEntity
import com.fiap.fastfood.core.valueObject.ProductCategory

interface ProductUseCase {

    fun create(product: ProductDTO)

    fun findByCategory(category: ProductCategory): List<ProductEntity>

    fun update(id: Long, product: ProductDTO)

    fun delete(id: Long)
}