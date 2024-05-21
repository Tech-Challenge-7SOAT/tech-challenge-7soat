package com.fiap.fastfood.core.entity

import com.fiap.fastfood.core.domain.Product
import com.fiap.fastfood.core.valueObject.Category
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "products")
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private val id: String,

    @Column(name = "is_active")
    private val isActive: Boolean,

    private val name: String,

    private val description: String,

    private val price: Double,

    @Column(name = "time_to_prepare")
    private val timeToPrepare: Int,

    private val category: Category,

    @ManyToMany(mappedBy = "products")
    private val orders: List<OrderEntity>,

    @Column(name = "created_at")
    @CreatedDate
    private val createdAt: LocalDateTime,

    @Column(name = "updated_at")
    @LastModifiedDate
    private val updatedAt: LocalDateTime
) {
    fun toDomain(): Product {
        return Product(id, isActive, name, description, price, timeToPrepare, category, orders.map { it.toDomain() }, createdAt, updatedAt)
    }
}