package com.fiap.fastfood.core.application.port.gateway

import com.fiap.fastfood.core.entity.ProductEntity
import com.fiap.fastfood.core.valueObject.ProductCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface  ProductRepository : JpaRepository<ProductEntity, Long> {
    fun findByCategory(category: ProductCategory): List<ProductEntity>

    fun findAllByIdIn(ids: List<Long>): List<ProductEntity>
}