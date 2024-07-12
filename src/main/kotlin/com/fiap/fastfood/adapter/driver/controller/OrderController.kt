package com.fiap.fastfood.adapter.driver.controller

import com.fiap.fastfood.core.application.port.presenter.OrderPresenter
import com.fiap.fastfood.core.application.useCase.order.OrderUseCase
import com.fiap.fastfood.core.dto.OrderDTO
import com.fiap.fastfood.core.valueObject.Status
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
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
    fun getOrder(@PathVariable id: Long): ResponseEntity<Any> {
        return ResponseEntity.ok(presenter.toDTO(orderUseCase.findOrderById(id)))
    }

    @PostMapping("/order")
    @Operation(summary = "Create a new order")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Order updated"),
            ApiResponse(responseCode = "201", description = "Order created"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun saveOrder(@RequestBody order: OrderDTO): ResponseEntity<Any> {
        return ResponseEntity.ok(presenter.toDTO(orderUseCase.save(order)))
    }

    @PatchMapping("/order")
    @Operation(summary = "Edit an order")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Order updated"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun editOrder(@RequestBody order: OrderDTO): ResponseEntity<Any> {
        if (order.id == null) {
            throw IllegalArgumentException()
        }

        return ResponseEntity.ok(presenter.toDTO(orderUseCase.save(order)))
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
    ): ResponseEntity<Any> {
        val orders = orderUseCase.listOrders(status)
        return ResponseEntity.ok(orders.map { order -> presenter.toDTO(order) })
    }
}