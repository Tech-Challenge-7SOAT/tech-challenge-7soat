package com.fiap.fastfood.core.application.port.presenter

import com.fiap.fastfood.core.dto.order.OrderProductsItensDTO
import com.fiap.fastfood.core.dto.product.ProductDTO
import com.fiap.fastfood.core.entity.OrderProductEntity
import org.springframework.stereotype.Component

@Component
class OrderProductPresenter {

    fun toDTO(orderProductEntity: OrderProductEntity): OrderProductsItensDTO {
        return OrderProductsItensDTO(
            quantity = orderProductEntity.quantity,
            product = ProductDTO(
                id = orderProductEntity.product.id,
                isActive = orderProductEntity.product.isActive,
                name = orderProductEntity.product.name,
                description = orderProductEntity.product.description,
                price = orderProductEntity.product.price,
                timeToPrepare = orderProductEntity.product.timeToPrepare,
                category = orderProductEntity.product.category,
                createdAt = orderProductEntity.product.createdAt,
                updatedAt = orderProductEntity.product.updatedAt
            )
        )
    }
}