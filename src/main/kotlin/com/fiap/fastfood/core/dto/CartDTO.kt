package com.fiap.fastfood.core.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull


data class CartDTO(
    @field:NotBlank
    val id: String,

    @field:NotEmpty
    val items: List<CartItemDTO>,

    @field:NotNull
    val totalAmount: Double
)
