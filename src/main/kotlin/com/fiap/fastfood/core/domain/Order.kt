package com.fiap.fastfood.core.domain

import com.fiap.fastfood.core.entity.OrderEntity
import com.fiap.fastfood.core.valueObject.Status
import java.time.LocalDateTime

class Order(
        private val id: String,
        private val totalAmount: Double,
        private val customerId: String,
        private val isPayed: Boolean,
        private val status: Status,
        private val products: List<Any>,
        private val createdAt: LocalDateTime,
        private val updatedAt: LocalDateTime
) {
    fun hasCombo(): Boolean {
        return this.products.isNotEmpty()
    }

    fun convertToEntity(): OrderEntity {
        return OrderEntity(id, totalAmount, customerId, isPayed, status, products, createdAt, updatedAt)
    }
}