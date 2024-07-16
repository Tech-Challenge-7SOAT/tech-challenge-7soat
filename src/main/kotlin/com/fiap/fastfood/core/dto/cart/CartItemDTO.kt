package com.fiap.fastfood.core.dto.cart

import jakarta.validation.constraints.NotBlank

data class CartItemDTO(
    @field:NotBlank
    val id: String,

    @field:NotBlank
    val name: String,

    @field:NotBlank
    val amount: Int,

    @field:NotBlank
    val price: Double
)
