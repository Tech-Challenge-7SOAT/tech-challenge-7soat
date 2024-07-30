package com.fiap.fastfood.core.dto.order

import com.fiap.fastfood.core.dto.customer.CustomerDTO
import com.fiap.fastfood.core.dto.product.ProductDTO
import com.fiap.fastfood.core.entity.OrderEntity
import com.fiap.fastfood.core.entity.ProductEntity
import com.fiap.fastfood.core.valueObject.Status
import jakarta.validation.constraints.NotEmpty
import java.sql.Timestamp

data class OrderDTO(
    val id: Long? = null,
    val customer: CustomerDTO?,
    val isPayed: Boolean,
    val status: Status,
    @field:NotEmpty val products: List<OrderProductsItensDTO>,
    val createdAt: Timestamp? = null,
    val updatedAt: Timestamp? = null
) {
    val totalAmount: Double
        get() {
            return products.sumOf { it.product.price * it.quantity }
        }

    val timeToPrepare: Int
        get() {
            return products.sumOf { it.product.timeToPrepare * it.quantity }
        }

    fun toEntity(order: OrderEntity, productsEntities: List<ProductEntity>): OrderEntity {
        return OrderEntity(
            id,
            totalAmount,
            timeToPrepare,
            customer?.toEntity(),
            isPayed,
            status,
            products.zip(productsEntities).map { (productDTO, productEntity) -> productDTO.toEntity(order, productEntity) }.toMutableList(),
            createdAt,
            updatedAt
        )
    }
}
