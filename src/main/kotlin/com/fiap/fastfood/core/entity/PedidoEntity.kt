package com.fiap.fastfood.core.entity

import com.fiap.fastfood.core.domain.Pedido
import com.fiap.fastfood.core.valueObject.Combo
import com.fiap.fastfood.core.valueObject.Status
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.LocalDateTime

@Entity
class PedidoEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private val id: String,
        private val totalAmount: Double,
        @Column(nullable = false)
        @ManyToOne
        private val customerId: String,
        private val isPayed: Boolean,
        private val status: Status,
        @Column(nullable = false)
        @OneToMany
        private val combos: List<Combo>,
        private var createdAt: Timestamp,
        private var updatedAt: Timestamp
) {
    @PrePersist
    fun onCreate() {
        createdAt = Timestamp.valueOf(LocalDateTime.now())
    }

    @PreUpdate
    fun onUpdate() {
        updatedAt = Timestamp.valueOf(LocalDateTime.now())
    }

    fun toPedido(): Pedido {
        return Pedido(id, totalAmount, customerId, isPayed, status, combos, createdAt, updatedAt)
    }
}