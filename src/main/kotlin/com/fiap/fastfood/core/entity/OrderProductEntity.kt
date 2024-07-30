package com.fiap.fastfood.core.entity

import jakarta.persistence.*

@Entity
@Table(name = "tb_order_products")
class OrderProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    var order: OrderEntity?,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    var product: ProductEntity,

    @Column(name = "quantity", nullable = false)
    var quantity: Int
)