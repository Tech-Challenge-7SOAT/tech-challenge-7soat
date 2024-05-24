package com.fiap.fastfood.core.application.port.service

import com.fiap.fastfood.core.domain.Product

open interface ProductService {

    fun create(product: Product)

    fun findByCategory(category: String): List<Product>

    fun update(id: Long, product: Product)

    fun delete(id: Long)
}