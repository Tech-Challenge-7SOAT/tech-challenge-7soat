package com.fiap.fastfood.core.entity

import com.fiap.fastfood.core.domain.Customer
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "customers")
class CustomerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private val id: String,

    private val firstName: String,

    private val lastName: String,

    private val cpf: String,

    private val email: String,

    private val phoneNumber: String,

    @OneToMany(mappedBy = "customer")
    private val orders: List<OrderEntity>,

    @Column(name = "created_at")
    @CreatedDate
    private val createdAt: LocalDateTime,
) {
    fun toDomain(): Customer {
        return Customer(id, firstName, lastName, cpf, email, phoneNumber,
            orders.map { it.toDomain() }, createdAt
        )
    }
}