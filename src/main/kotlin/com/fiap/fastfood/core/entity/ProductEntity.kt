package com.fiap.fastfood.core.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fiap.fastfood.core.valueObject.ProductCategory
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp

@Entity
@Table(name = "tb_products")
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "is_active", nullable = false)
    var isActive: Boolean = true,

    @Column(name = "name", nullable = false)
    var name: String = "",

    @Column(name = "description", nullable = false)
    var description: String = "",

    @Column(name = "price", nullable = false)
    var price: Double = 0.0,

    @ManyToMany(mappedBy = "products", cascade = [CascadeType.REMOVE])
    val order: MutableList<OrderEntity> = mutableListOf(),

    @Column(name = "time_to_prepare", nullable = false)
    var timeToPrepare: Int = 0,

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    var category: ProductCategory,

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss'Z'")
    @Column(name = "created_at")
    val createdAt: Timestamp? = null,

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss'Z'")
    @Column(name = "updated_at")
    val updatedAt: Timestamp? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss'Z'")
    @Column(name = "deleted_at")
    val deletedAt: Timestamp? = null
)
