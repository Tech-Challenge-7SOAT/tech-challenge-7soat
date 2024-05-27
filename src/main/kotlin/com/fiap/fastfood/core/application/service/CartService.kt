package com.fiap.fastfood.core.application.service

import com.fiap.fastfood.core.domain.Cart
import org.springframework.stereotype.Service

@Service
class CartService {

    fun checkout(cart: Cart): Cart = cart
}
