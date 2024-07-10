package com.fiap.fastfood.core.application.useCase

import com.fiap.fastfood.core.dto.CartDTO

interface CartUseCase {
    fun checkout(cart: CartDTO): CartDTO
}