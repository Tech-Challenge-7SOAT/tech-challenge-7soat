package com.fiap.fastfood.adapter.driver

import com.fiap.fastfood.core.application.port.service.CartService
import com.fiap.fastfood.core.domain.Cart
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckoutController(private val cartService: CartService) {

    @PostMapping("/checkout")
    @Operation(summary = "Realiza o checkout do carrinho de compras")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Checkout realizado com sucesso"),
        ApiResponse(responseCode = "400", description = "Erro ao realizar o checkout")
    ])
    fun index(@Validated @RequestBody cart: Cart) = this.cartService.checkout(cart)
}
