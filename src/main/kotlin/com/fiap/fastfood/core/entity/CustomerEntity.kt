package com.fiap.fastfood.core.entity

import com.fiap.fastfood.core.domain.Customer
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "customers")
class CustomerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String?,

    val cpf: String,

    val email: String,

    val phoneNumber: String,

    @OneToMany(mappedBy = "customer")
    val orders: List<OrderEntity>?,

    @Column(name = "created_at")
    @CreatedDate
    val createdAt: LocalDateTime?,

    @Column(name = "updated_at")
    @LastModifiedDate
    val updatedAt: LocalDateTime?
) {
    fun toDomain(): Customer {
        return Customer(id, cpf, email, phoneNumber, orders?.map { it.toDomain() }, createdAt, updatedAt)
    }
}