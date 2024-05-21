package com.fiap.fastfood.core.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fiap.fastfood.core.domain.Product
import com.fiap.fastfood.core.domain.enumeration.ProductCategory
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.sql.Timestamp

@Entity
@Table(name = "tb_products")
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    val id: Long = 0,

    @Column(name = "is_active", nullable = false)
    var isActive: Boolean = true,

    @Column(name = "name", nullable = false, unique = true)
    var name: String = "",

    @Column(name = "description", nullable = false)
    var description: String = "",

    @Column(name = "price", nullable = false)
    var price: Double = 0.0,

    @Column(name = "time_to_prepare", nullable = false)
    var timeToPrepare: Int = 0,

//    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    var category: String = "",

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss'Z'")
    val createdAt: Timestamp? = null,

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss'Z'")
    val updatedAt: Timestamp? = null
) {

    fun toDomain(): Product {
        return Product(
            id = id,
            isActive = isActive,
            name = name,
            description = description,
            price = price,
            timeToPrepare = timeToPrepare,
            category = category,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}