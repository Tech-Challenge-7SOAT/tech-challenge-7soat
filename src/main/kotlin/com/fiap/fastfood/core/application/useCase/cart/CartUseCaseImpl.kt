package com.fiap.fastfood.core.application.useCase.cart

import com.fiap.fastfood.core.dto.cart.CartDTO
import org.springframework.stereotype.Service

@Service
class CartUseCaseImpl : CartUseCase {

    override fun checkout(cart: CartDTO): CartDTO = cart
}
