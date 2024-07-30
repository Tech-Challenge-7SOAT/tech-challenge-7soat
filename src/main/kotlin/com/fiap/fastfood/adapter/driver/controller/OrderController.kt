package com.fiap.fastfood.adapter.driver.controller

import com.fiap.fastfood.core.application.port.presenter.OrderPresenter
import com.fiap.fastfood.core.application.useCase.order.OrderUseCase
import com.fiap.fastfood.core.dto.order.OrderDTO
import com.fiap.fastfood.core.dto.order.OrderRequestCreateDTO
import com.fiap.fastfood.core.dto.order.OrderRequestUpdateDTO
import com.fiap.fastfood.core.valueObject.Status
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Order", description = "Create, remove, edit and search orders by status")
@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderUseCase: OrderUseCase,
    private val presenter: OrderPresenter
) {
    @GetMapping("/order/{id}")
    @Operation(summary = "Get order for the given id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Order found"),
            ApiResponse(responseCode = "400", description = "Invalid id type"),
            ApiResponse(responseCode = "404", description = "Order not found"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun getOrder(@PathVariable(required = true) id: Long): ResponseEntity<OrderDTO> {
        return ResponseEntity.ok(presenter.toDTO(orderUseCase.findOrderById(id)))
    }

    @PostMapping("/order")
    @Operation(summary = "Create a new order")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Order created"),
            ApiResponse(responseCode = "400", description = "CPF is required"),
            ApiResponse(responseCode = "404", description = "Customer with this CPF does not exist."),
            ApiResponse(responseCode = "404", description = "Product not found for IDs: xpto")
        ]
    )
    fun saveOrder(
        @RequestBody order: OrderRequestCreateDTO
    ): ResponseEntity<OrderDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(presenter.toDTO(orderUseCase.create(order)))
    }

    @PatchMapping("/order")
    @Operation(summary = "Edit an order")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Order updated"),
            ApiResponse(responseCode = "400", description = "Order already finalized when status is equals to FINALIZADO"),
            ApiResponse(responseCode = "404", description = "Order not found"),
            ApiResponse(responseCode = "404", description = "Customer with this CPF does not exist."),
            ApiResponse(responseCode = "404", description = "Product not found for IDs: xpto")
        ]
    )
    fun editOrder(
        @Valid @RequestBody order: OrderRequestUpdateDTO
    ): ResponseEntity<OrderDTO> {
        return ResponseEntity.ok(presenter.toDTO(orderUseCase.update(order)))
    }

    @DeleteMapping("/order/{id}")
    @Operation(summary = "Delete a order for the given id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Order deleted"),
            ApiResponse(responseCode = "400", description = "Invalid id type"),
            ApiResponse(responseCode = "404", description = "Order not found"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun deleteOrder(@PathVariable id: Long): ResponseEntity<Any> {
        orderUseCase.deleteOrderById(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping
    @Operation(summary = "Get all orders")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Orders retrieved"),
            ApiResponse(responseCode = "404", description = "No orders found"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun getOrders(
        @RequestParam(required = false) status: Status?
    ): ResponseEntity<List<OrderDTO>> {
        val orders = orderUseCase.listOrders(status)
        return ResponseEntity.ok(orders.map { order -> presenter.toDTO(order) })
    }

    @GetMapping("/order/{id}/status")
    @Operation(summary = "Get order status for the given id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Order status found"),
            ApiResponse(responseCode = "404", description = "Order not found"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun getOrderStatus(@PathVariable(required = true) id: Long): ResponseEntity<Any> {
        val order = orderUseCase.findOrderById(id)
        return ResponseEntity.ok(
            mapOf(
                "orderId" to order.id,
                "status" to order.status
            )
        )
    }
}
