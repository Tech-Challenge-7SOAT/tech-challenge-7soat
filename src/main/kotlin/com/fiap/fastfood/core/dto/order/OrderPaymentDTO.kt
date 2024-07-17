package com.fiap.fastfood.core.dto.order

import com.fiap.fastfood.core.valueObject.Status

data class OrderPaymentDTO(
    val orderId: Long,
    val status: Status
)
