package com.fiap.fastfood.core.application.useCase.payment

import com.fiap.fastfood.core.application.port.gateway.PaymentGateway
import com.fiap.fastfood.core.dto.payment.PaymentDTO
import org.springframework.stereotype.Service

@Service
class PaymentUseCaseImpl(private val paymentGateway: PaymentGateway) : PaymentUseCase {
    override fun pay(paymentDTO: PaymentDTO) = paymentGateway.pay(paymentDTO)
}
