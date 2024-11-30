package com.fiap.fastfood.core.application.useCase.payment

import com.fiap.fastfood.core.application.port.gateway.PaymentGateway
import com.fiap.fastfood.core.dto.payment.PaymentDTO
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.math.BigDecimal

@Profile("test")
@SpringBootTest
@AutoConfigureDataJpa
class PaymentUseCaseImplTest {

    private lateinit var paymentUseCaseImpl: PaymentUseCaseImpl
    private val paymentGateway: PaymentGateway = mockk()

    @BeforeEach
    fun setUp() {
        paymentUseCaseImpl = PaymentUseCaseImpl(paymentGateway)
    }

    @Test
    fun pay() {
        every { paymentGateway.pay(any()) } returns "SUCCESS"

        val result = paymentUseCaseImpl.pay(PaymentDTO(
            orderId = 1L,
            paymentType = "CREDIT",
            amount = BigDecimal.ONE
        ))

        expectThat(result).isEqualTo("SUCCESS")
    }

    @Test
    fun payThrowException() {
        every { paymentGateway.pay(any()) } throws Exception()

        org.junit.jupiter.api.assertThrows<Exception> {
            paymentUseCaseImpl.pay(PaymentDTO(
                orderId = 1L,
                paymentType = "CREDIT",
                amount = BigDecimal.ONE
            ))
        }
    }
}
