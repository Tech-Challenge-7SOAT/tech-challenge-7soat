package com.fiap.fastfood.core.dto.order

import com.fiap.fastfood.core.valueObject.Status

data class OrderRequestUpdateDTO(
    val id: Long,
    val cpf: String?,
    val status: Status?,
    val productIds: List<ProductsItens>?
)
