package com.fiap.fastfood.core.application.useCase.payment

import com.fiap.fastfood.core.dto.payment.PaymentDTO

interface PaymentUseCase {
    fun pay(paymentDTO: PaymentDTO): String
}
