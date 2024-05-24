package com.fiap.fastfood.core.domain

import com.fiap.fastfood.core.entity.CustomerEntity
import java.time.LocalDateTime

class Customer(
    private val id: String?,
    private val cpf: String,
    private val email: String,
    private val phoneNumber: String,
    private val orders: List<Order>?,
    private val createdAt: LocalDateTime?,
    private val updatedAt: LocalDateTime?
) {
    fun toEntity(): CustomerEntity {
        return CustomerEntity(id, cpf, email, phoneNumber, orders?.map { it.toEntity() }, createdAt, updatedAt)
    }
}