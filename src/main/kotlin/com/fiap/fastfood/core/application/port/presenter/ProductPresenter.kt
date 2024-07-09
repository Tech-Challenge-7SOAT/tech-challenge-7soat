package com.fiap.fastfood.core.application.port.presenter

import com.fiap.fastfood.core.dto.ProductDTO
import com.fiap.fastfood.core.entity.ProductEntity

class ProductPresenter(private val product: ProductDTO){

    fun toDTO(productEntity: ProductEntity): ProductDTO {
        return ProductDTO(
            id = product.id,
            isActive = product.isActive,
            name = product.name,
            description = product.description,
            price = product.price,
            timeToPrepare = product.timeToPrepare,
            category = product.category,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt
        )
    }
}