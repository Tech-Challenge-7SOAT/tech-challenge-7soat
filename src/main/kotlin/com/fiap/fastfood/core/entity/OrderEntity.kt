package com.fiap.fastfood.core.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
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
    val id: Long?,

    @Column(name = "total_amount", nullable = false)
    val totalAmount: Double?,

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    var customer: CustomerEntity?,

    @Column(name = "is_payed")
    val isPayed: Boolean = false,

    val status: Status,

    @ManyToMany
    @JoinTable(
        name = "tb_combos",
        joinColumns = [JoinColumn(name = "order_id")],
        inverseJoinColumns = [JoinColumn(name = "product_id")]
    )
    var products: MutableList<ProductEntity> = mutableListOf(),

    @Column(name = "created_at")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss'Z'")
    val createdAt: Timestamp? = null,

    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss'Z'")
    val updatedAt: Timestamp? = null
) {
    constructor() : this(
        -1L,
        null,
        CustomerEntity(-1L, "", "", "", "", ""),
        false,
        Status.PENDENTE,
        mutableListOf(),
        null,
        null
    )

    fun toDomain(): Order {
        return Order(id, customer?.toDomain(), isPayed, status, products.map { it.toDomain() }, createdAt, updatedAt)
    }
}
