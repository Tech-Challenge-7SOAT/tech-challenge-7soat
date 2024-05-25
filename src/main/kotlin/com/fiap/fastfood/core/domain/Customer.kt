package com.fiap.fastfood.core.domain

import com.fiap.fastfood.core.entity.CustomerEntity
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import java.sql.Timestamp

data class Customer(
    val id: Long,
    @field:NotBlank
    val firstName: String,
    @NotBlank
    val lastName: String,
    @field:NotBlank
    val cpf: String,
    @field:NotBlank
    @Email
    val email: String,
    @field:NotBlank
    val phoneNumber: String,
//    val orders: List<Order>?,
    val createdAt: Timestamp? = null,
)  {
    fun toEntity(): CustomerEntity {
        return CustomerEntity(id, firstName, lastName, cpf, email, phoneNumber
//            orders!!.map { it.toEntity() },
        )
    }
}