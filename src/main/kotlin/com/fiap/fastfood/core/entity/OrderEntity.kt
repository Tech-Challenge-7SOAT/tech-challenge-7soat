package com.fiap.fastfood.core.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fiap.fastfood.core.domain.Order
import com.fiap.fastfood.core.valueObject.Status
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "tb_orders")
class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,

    @Column(name = "total_amount", nullable = false)
    private val totalAmount: Double?,

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private val customer: CustomerEntity,

    @Column(name = "is_payed")
    private val isPayed: Boolean = false,

    private val status: Status,

    @ManyToMany
    @JoinTable(
        name = "tb_combos",
        joinColumns = [JoinColumn(name = "order_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "product_id", referencedColumnName = "id")]
    )
    private val products: List<ProductEntity>,

    @Column(name = "created_at")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss'Z'")
    private val createdAt: Timestamp? = null,

    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss'Z'")
    private val updatedAt: Timestamp? = null
) {
    fun toDomain(): Order {
        return Order(id, totalAmount!!, customer.id!!, isPayed, status, products.map { it.toDomain() }, createdAt, updatedAt)
    }
}