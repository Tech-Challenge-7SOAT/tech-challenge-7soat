package com.fiap.fastfood.core.entity

import com.fiap.fastfood.core.domain.Customer
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@Table(name = "tb_customers")
class CustomerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    val id: Long = 0,

    @Column(name = "first_name", nullable = false)
    var firstName: String = "",

    @Column(name = "last_name", nullable = false)
    val lastName: String = "",

    @Column(name = "cpf", nullable = false)
    val cpf: String = "",

    @Column(name = "email", nullable = false)
    val email: String = "",

    @Column(name = "phone_number", nullable = false)
    val phoneNumber: String = "",

    @OneToMany(mappedBy = "customer")
    @Column(name = "order", nullable = false)
    val orders: List<OrderEntity>? = null,

    @Column(name = "created_at")
    @CreatedDate
    val createdAt: LocalDateTime? = null,
) {
    fun toDomain(): Customer {
        return Customer(id, firstName, lastName, cpf, email, phoneNumber,
            orders?.map { it.toDomain() }, createdAt
        )
    }
}