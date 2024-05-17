package com.fiap.fastfood.core.entity

import com.fiap.fastfood.core.domain.Order
import com.fiap.fastfood.core.valueObject.Status
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
class OrderEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private val id: String,

        @Column(name = "total_amount", nullable = false)
        private val totalAmount: Double,

        @Column(name = "customer_id", nullable = false)
        @ManyToOne
        @JoinColumn(name = "customer_id", referencedColumnName = "id")
        private val customerId: String,

        private val isPayed: Boolean,
        private val status: Status,

        @Column(nullable = false)
        @ManyToMany
        @JoinTable(
                name = "combos",
                joinColumns = [JoinColumn(name = "order_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "product_id", referencedColumnName = "id")]
        )
        private val products: List<Any>,

        @Column(name = "created_at")
        @CreatedDate
        private var createdAt: LocalDateTime,

        @Column(name = "updated_at")
        @LastModifiedDate
        private var updatedAt: LocalDateTime
) {
    fun convertToOrder(): Order {
        return Order(id, totalAmount, customerId, isPayed, status, products, createdAt, updatedAt)
    }
}