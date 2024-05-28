package com.fiap.fastfood.core.application.port.service

import com.fiap.fastfood.core.domain.Product
import com.fiap.fastfood.core.valueObject.ProductCategory

interface ProductService {

    fun create(product: Product)

    fun findByCategory(category: ProductCategory): List<Product>

    fun update(id: Long, product: Product)

    fun delete(id: Long)
}