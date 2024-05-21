package com.fiap.fastfood.core.application.port.repository

import com.fiap.fastfood.core.domain.enumeration.ProductCategory
import com.fiap.fastfood.core.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface  ProductRepository : JpaRepository<ProductEntity, Long> {
    fun findByCategory(category: String): List<ProductEntity>
}