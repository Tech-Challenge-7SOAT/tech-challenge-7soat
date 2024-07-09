package com.fiap.fastfood.core.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty


data class CartDTO(
    @field:NotBlank
    val id: String,

    @field:NotEmpty
    val items: List<CartItemDTO>
)
