package com.fiap.fastfood.core.entity

import com.fiap.fastfood.core.domain.Order
import com.fiap.fastfood.core.valueObject.Status
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "orders")
class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private val id: String?,

    @Column(name = "total_amount", nullable = false)
    private val totalAmount: Double?,

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private val customer: CustomerEntity,

    private val isPayed: Boolean,

    private val status: Status,

    @ManyToMany
    @JoinTable(
        name = "combos",
        joinColumns = [JoinColumn(name = "order_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "product_id", referencedColumnName = "id")]
    )
    private val products: List<ProductEntity>,

    @Column(name = "created_at")
    @CreatedDate
    private val createdAt: Timestamp? = null,

    @Column(name = "updated_at")
    @LastModifiedDate
    private val updatedAt: Timestamp? = null
) {
    fun toDomain(): Order {
        return Order(id, totalAmount!!,
            customer.id, isPayed, status, products.map { it.toDomain() }, createdAt, updatedAt)
    }
}