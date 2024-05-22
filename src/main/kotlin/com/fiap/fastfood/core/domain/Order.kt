package com.fiap.fastfood.core.domain

import com.fiap.fastfood.core.entity.OrderEntity
import com.fiap.fastfood.core.valueObject.Status
import java.time.LocalDateTime

class Order(
    private val id: String,
    private val totalAmount: Double,
    private val customer: Customer,
    private val isPayed: Boolean,
    private val status: Status,
    private val products: List<Product>,
    private val createdAt: LocalDateTime,
    private val updatedAt: LocalDateTime
) {
    fun hasCombo(): Boolean {
        return this.products.isNotEmpty()
    }

    fun toEntity(): OrderEntity {
        return OrderEntity(id, totalAmount, customer.toEntity(), isPayed, status, products.map { it.toEntity() }, createdAt, updatedAt)
    }
}