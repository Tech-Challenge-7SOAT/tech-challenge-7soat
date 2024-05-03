package com.fiap.fastfood.core.domain

import java.time.LocalDate

data class Cliente(
    val id: String? = null,
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val email: String,
    val createdAt: LocalDate,
    val updatedAt: LocalDate,
    val createdBy: String,
    val updatedBy: String
) {
}