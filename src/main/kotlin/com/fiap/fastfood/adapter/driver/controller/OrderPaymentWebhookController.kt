package com.fiap.fastfood.adapter.driver.controller

import com.fiap.fastfood.core.application.useCase.order.OrderUseCaseImpl
import com.fiap.fastfood.core.dto.order.OrderPaymentDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import io.swagger.v3.oas.annotations.parameters.RequestBody as DocRequestBody

@Tag(name = "Webhook", description = "Receive payment webhook")
@RestController
@RequestMapping("/webhook/payment")
class OrderPaymentWebhookController(private val orderUseCase: OrderUseCaseImpl) {

    @PostMapping
    @Operation(summary = "Receive payment webhook")
    @DocRequestBody(
        content = [Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = Schema(implementation = OrderPaymentDTO::class),
            examples = [
                ExampleObject(name = "Pagamento recebido", value = "{\"orderId\": 1,\"status\": \"RECEBIDO\"}"),
                ExampleObject(name = "Pagamento recusado", value = "{\"orderId\": 1,\"status\": \"PAGAMENTO_RECUSADO\"}")
            ]
        )]
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Payment webhook received"),
            ApiResponse(responseCode = "404", description = "Order not found"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun index(@RequestBody request: OrderPaymentDTO) =
        orderUseCase.updateStatus(request.orderId, request.status)
}
