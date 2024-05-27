package com.fiap.fastfood.core.domain

import com.fiap.fastfood.core.entity.CustomerEntity
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.sql.Timestamp

data class Customer(
    val id: Long,
    val firstName: String,
    val lastName: String,
    @field:NotEmpty
    val cpf: String,
    @Email
    val email: String,
    val phoneNumber: String,
    val createdAt: Timestamp? = null,
    val updatedAt: Timestamp? = null
)  {
    fun toEntity(): CustomerEntity {
        return CustomerEntity(id, firstName, lastName, cpf, email, phoneNumber)
    }
}