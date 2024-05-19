package com.fiap.fastfood.core.domain

import jakarta.validation.constraints.NotBlank

data class CartItem(
    @field:NotBlank
    val id: String,

    @field:NotBlank
    val name: String,

    @field:NotBlank
    val amount: Int,

    @field:NotBlank
    val price: Double
)
