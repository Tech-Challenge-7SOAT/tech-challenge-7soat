package com.fiap.fastfood.core.entity

import com.fasterxml.jackson.annotation.JsonFormat
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
    val id: Long? = null,

    @Column(name = "total_amount", nullable = false)
    var totalAmount: Double,

    @Column(name = "time_to_prepare", nullable = false, columnDefinition = "integer default 0")
    var timeToPrepare: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    var customer: CustomerEntity?,

    @Column(name = "is_payed")
    var isPayed: Boolean = false,

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    var status: Status,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    var products: MutableList<OrderProductEntity> = mutableListOf(),

    @Column(name = "created_at")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss'Z'")
    val createdAt: Timestamp? = null,

    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss'Z'")
    val updatedAt: Timestamp? = null,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    var orderProducts: MutableList<OrderProductEntity> = mutableListOf()
) {

    constructor() : this(
        -1L,
        0.0,
        0,
        CustomerEntity(-1L, "", "", "", "", ""),
        false,
        Status.PENDENTE,
        mutableListOf(),
        null,
        null
    )
}
