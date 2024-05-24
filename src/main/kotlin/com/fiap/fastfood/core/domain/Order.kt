package com.fiap.fastfood.core.domain

import com.fiap.fastfood.core.entity.OrderEntity
import com.fiap.fastfood.core.valueObject.Status
import jakarta.validation.constraints.NotEmpty
import java.sql.Timestamp

class Order(
    val id: String? = null,
    val totalAmount: Double,
    val customerId: String,
    val isPayed: Boolean,
    val status: Status,
    @field:NotEmpty val products: List<Product>,
    val createdAt: Timestamp? = null,
    val updatedAt: Timestamp? = null
) {
    fun hasCombo(): Boolean {
        return this.products.isNotEmpty()
    }

    fun toEntity(): OrderEntity {
        return OrderEntity(
            id,
            totalAmount,
            Customer(customerId, null.toString(), null.toString(), null.toString(), null, null, null).toEntity(),
            isPayed,
            status,
            products.map { it.toEntity() },
            createdAt,
            updatedAt
        )
    }
}