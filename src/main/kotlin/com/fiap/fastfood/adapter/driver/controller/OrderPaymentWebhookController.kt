package com.fiap.fastfood.adapter.driver.controller

import com.fiap.fastfood.core.application.port.service.OrderPaymentService
import com.fiap.fastfood.core.entity.OrderPayment
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "OrderPaymentWebhook", description = "Receive payment webhook")
@RestController
@RequestMapping("/webhook/payment")
class OrderPaymentWebhookController(
    private val orderPaymentService: OrderPaymentService
) {

    @PostMapping
    @Operation(summary = "Receive payment webhook")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Payment webhook received"),
            ApiResponse(responseCode = "404", description = "Order not found"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun index(@RequestBody request: OrderPayment) =
        orderPaymentService.save(request.id, request.status)
}
