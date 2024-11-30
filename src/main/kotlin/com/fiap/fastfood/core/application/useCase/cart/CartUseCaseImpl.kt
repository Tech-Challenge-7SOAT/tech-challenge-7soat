package com.fiap.fastfood.core.application.useCase.cart

import com.fiap.fastfood.core.application.port.gateway.KitchenGateway
import com.fiap.fastfood.core.dto.cart.CartDTO
import com.fiap.fastfood.core.dto.checkout.KitchenPreparationDTO
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class CartUseCaseImpl(private val kitchenGateway: KitchenGateway) : CartUseCase {

    override fun checkout(cart: CartDTO): CartDTO {
        val preparation = KitchenPreparationDTO(
            cart.id,
            LocalDate.now().plusDays(1).toString(),
            "PENDING"
        )

        kitchenGateway.prepare(preparation)
        return cart
    }
}
