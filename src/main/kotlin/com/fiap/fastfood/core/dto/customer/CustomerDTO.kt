package com.fiap.fastfood.core.dto.customer

import com.fiap.fastfood.core.entity.CustomerEntity
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import java.sql.Timestamp

data class CustomerDTO(
    val id: Long,
    val firstName: String,
    val lastName: String,
    @field:NotEmpty
    val cpf: String,
    @Email
    val email: String,
    val phoneNumber: String,
    val createdAt: Timestamp? = null
)  {
    fun toEntity(): CustomerEntity {
        return CustomerEntity(id, firstName, lastName, cpf, email, phoneNumber)
    }
}