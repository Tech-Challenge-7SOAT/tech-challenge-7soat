package com.fiap.fastfood.core.dto.order

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class OrderRequestCreateDTO(
    val cpf: String,
    @field:NotNull
    @field:NotEmpty
    val productIds: List<ProductsItens>
)

data class ProductsItens(
    val id: Long,
    val quantity: Int
)