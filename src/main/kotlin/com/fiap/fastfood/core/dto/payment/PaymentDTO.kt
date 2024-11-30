package com.fiap.fastfood.core.dto.payment

import java.math.BigDecimal

data class PaymentDTO(
    val orderId: Long,
    val paymentType: String,
    val amount: BigDecimal
)
