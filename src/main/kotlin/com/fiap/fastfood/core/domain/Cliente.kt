package com.fiap.fastfood.core.domain

import java.util.*

interface Cliente {
    val id: Long
    val firstName: String
    val lastName: String
    val cpf: String
    val email: String
    val createdAt: Date
    val updatedAt: Date
    val createdBy: String
    val updatedBy: String
}