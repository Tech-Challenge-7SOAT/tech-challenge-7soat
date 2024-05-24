package com.fiap.fastfood.core.domain

import com.fiap.fastfood.core.entity.CustomerEntity
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.time.LocalDateTime

data class Customer (
    val id: Long,
    val firstName: String,
    val lastName: String,
    @field:NotBlank
    @field:NotEmpty
    val cpf: String,
    val email: String,
    val phoneNumber: String,
    val orders: List<Order>? = emptyList(),
    val createdAt: LocalDateTime? = null,
)  {
    fun toEntity(): CustomerEntity {
        return CustomerEntity(id, firstName, lastName, cpf, email, phoneNumber,
            orders!!.map { it.toEntity() }, createdAt!!
        )
    }
}