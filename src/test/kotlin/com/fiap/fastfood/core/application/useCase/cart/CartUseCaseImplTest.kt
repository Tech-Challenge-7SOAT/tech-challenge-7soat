package com.fiap.fastfood.core.application.useCase.cart

import com.fiap.fastfood.core.application.port.gateway.KitchenGateway
import com.fiap.fastfood.core.dto.cart.CartDTO
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@Profile("test")
@SpringBootTest
@AutoConfigureDataJpa
class CartUseCaseImplTest {

    @Autowired
    private lateinit var cartUseCaseImpl: CartUseCaseImpl
    private val kitchenGateway: KitchenGateway = mockk()

    @BeforeEach
    fun setUp() {
        cartUseCaseImpl = CartUseCaseImpl(kitchenGateway)
    }

    @Test
    fun checkout() {
        every { kitchenGateway.prepare(any()) } returns "PENDING"

        val cart = CartDTO(
            id = "1",
            emptyList(),
            100.99
        )

        val result = cartUseCaseImpl.checkout(cart)

        expectThat(result).isEqualTo(cart)
    }

    @Test
    fun checkoutWithException() {
        every { kitchenGateway.prepare(any()) } throws Exception()

        val cart = CartDTO(
            id = "1",
            emptyList(),
            100.99
        )

        org.junit.jupiter.api.assertThrows<Exception> {
            cartUseCaseImpl.checkout(cart)
        }
    }
}
