package com.fiap.fastfood.core.domain

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty


data class Cart(
    @field:NotBlank
    val id: String,

    @field:NotEmpty
    val items: List<CartItem>
)
