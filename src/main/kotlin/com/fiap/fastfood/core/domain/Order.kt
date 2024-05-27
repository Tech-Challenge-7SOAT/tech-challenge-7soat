package com.fiap.fastfood.core.domain

import com.fiap.fastfood.core.entity.OrderEntity
import com.fiap.fastfood.core.valueObject.Status
import jakarta.validation.constraints.NotEmpty
import java.sql.Timestamp

class Order(
    val id: Long? = null,
    val customer: Customer?,
    val isPayed: Boolean,
    val status: Status,
    @field:NotEmpty val products: List<Product>,
    val createdAt: Timestamp? = null,
    val updatedAt: Timestamp? = null
) {
    val totalAmount: Double
        get() {
            return products.sumOf { it.price }
        }

    val timeToPrepare: Int
        get() {
            return products.sumOf { it.timeToPrepare }
        }

    fun hasCombo(): Boolean {
        return this.products.isNotEmpty()
    }

    fun toEntity(): OrderEntity {
        return OrderEntity(
            id,
            totalAmount,
            customer?.toEntity(),
            isPayed,
            status,
            products.map { it.toEntity() },
            createdAt,
            updatedAt
        )
    }
}