package com.fiap.fastfood.core.application.usecase

import com.fiap.fastfood.core.dto.CartDTO

interface CartUseCase {
    fun checkout(cart: CartDTO): CartDTO
}