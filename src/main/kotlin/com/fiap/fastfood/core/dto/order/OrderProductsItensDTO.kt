package com.fiap.fastfood.core.dto.order

import com.fiap.fastfood.core.dto.product.ProductDTO
import com.fiap.fastfood.core.entity.OrderEntity
import com.fiap.fastfood.core.entity.OrderProductEntity
import com.fiap.fastfood.core.entity.ProductEntity

data class OrderProductsItensDTO(
    val quantity: Int,
    val product: ProductDTO
) {
    fun toEntity(order: OrderEntity, productEntity: ProductEntity): OrderProductEntity {
        return OrderProductEntity(
            order = order,
            product = productEntity,
            quantity = this.quantity
        )
    }
}