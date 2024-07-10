package com.fiap.fastfood.core.application.useCase

import com.fiap.fastfood.core.dto.CartDTO
import org.springframework.stereotype.Service

@Service
class CartUseCaseImpl : CartUseCase {

    override fun checkout(cart: CartDTO): CartDTO = cart
}
