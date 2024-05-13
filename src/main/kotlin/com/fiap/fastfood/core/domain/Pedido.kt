package com.fiap.fastfood.core.domain

import com.fiap.fastfood.core.entity.PedidoEntity
import com.fiap.fastfood.core.valueObject.Combo
import com.fiap.fastfood.core.valueObject.Status
import java.sql.Timestamp

class Pedido(
        private val id: String,
        private val totalAmount: Double,
        private val customerId: String,
        private val isPayed: Boolean,
        private val status: Status,
        private val combos: List<Combo>,
        private val createdAt: Timestamp,
        private val updatedAt: Timestamp
) {
    fun hasCombo(): Boolean {
        return this.combos.isNotEmpty()
    }

    fun toEntity(): PedidoEntity {
        return PedidoEntity(id, totalAmount, customerId, isPayed, status, combos, createdAt, updatedAt)
    }
}