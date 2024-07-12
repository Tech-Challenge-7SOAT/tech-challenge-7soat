package com.fiap.fastfood.core.application.useCase.cart

import com.fiap.fastfood.core.dto.CartDTO

interface CartUseCase {
    fun checkout(cart: CartDTO): CartDTO
}