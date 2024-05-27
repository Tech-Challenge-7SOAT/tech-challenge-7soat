package com.fiap.fastfood.adapter.driver.controller

import com.fiap.fastfood.core.application.service.CartService
import com.fiap.fastfood.core.domain.Cart
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Checkout", description = "Finalize the shopping cart")
@RestController
class CheckoutController(private val cartService: CartService) {

    @PostMapping("/checkout")
    @Operation(summary = "Perform shopping cart checkout")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Checkout completed successfully"),
        ApiResponse(responseCode = "400", description = "Error when performing checkout")
    ])
    fun index(@Validated @RequestBody cart: Cart) = this.cartService.checkout(cart)
}
