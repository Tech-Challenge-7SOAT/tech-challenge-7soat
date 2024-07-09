package com.fiap.fastfood.core.application.usecase

import com.fiap.fastfood.core.dto.CartDTO
import org.springframework.stereotype.Service

@Service
class CartUseCaseImpl {

    fun checkout(cart: CartDTO): CartDTO = cart
}
