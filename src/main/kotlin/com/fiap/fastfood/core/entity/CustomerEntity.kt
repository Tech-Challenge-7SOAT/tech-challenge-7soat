package com.fiap.fastfood.core.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fiap.fastfood.core.domain.Customer
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.sql.Timestamp

@Entity
@Table(name = "tb_customers")
class CustomerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    val id: Long,

    @Column(name = "first_name", nullable = false)
    var firstName: String,

    @Column(name = "last_name", nullable = false)
    val lastName: String,

    @Column(name = "cpf", nullable = false)
    val cpf: String,

    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "phone_number", nullable = false)
    val phoneNumber: String,

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss'Z'")
    @Column(name = "created_at", nullable = false)
    val createdAt: Timestamp? = null,
) {
    fun toDomain(): Customer {
        return Customer(id, firstName, lastName, cpf, email, phoneNumber, createdAt)
    }
}