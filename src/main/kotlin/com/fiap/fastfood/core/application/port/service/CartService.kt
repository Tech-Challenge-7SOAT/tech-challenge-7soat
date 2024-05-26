package com.fiap.fastfood.core.application.port.service

import com.fiap.fastfood.core.domain.Cart
import org.springframework.stereotype.Service

@Service
class CartService {

    fun checkout(cart: Cart): Cart = cart
}
