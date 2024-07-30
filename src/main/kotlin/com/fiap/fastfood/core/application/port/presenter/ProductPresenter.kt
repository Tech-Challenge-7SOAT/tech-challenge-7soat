package com.fiap.fastfood.core.application.port.presenter

import com.fiap.fastfood.core.dto.product.ProductDTO
import com.fiap.fastfood.core.entity.ProductEntity
import org.springframework.stereotype.Component

@Component
class ProductPresenter {

    fun toDTO(productEntity: ProductEntity): ProductDTO {
        return ProductDTO(
            id = productEntity.id,
            isActive = productEntity.isActive,
            name = productEntity.name,
            description = productEntity.description,
            price = productEntity.price,
            timeToPrepare = productEntity.timeToPrepare,
            category = productEntity.category,
            createdAt = productEntity.createdAt,
            updatedAt = productEntity.updatedAt
        )
    }
}