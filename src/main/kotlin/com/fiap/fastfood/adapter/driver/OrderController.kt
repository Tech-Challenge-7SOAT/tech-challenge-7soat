package com.fiap.fastfood.adapter.driver

import com.fiap.fastfood.core.application.port.service.OrderService
import com.fiap.fastfood.core.domain.Order
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
class OrderController(private val orderService: OrderService) {
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
        return ResponseEntity.ok(orderService.findOrderById(id))
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
    fun saveOrder(@RequestBody order: Order): ResponseEntity<Any> {
        return ResponseEntity.ok(orderService.save(order))
    }

    @PutMapping("/order")
    @Operation(summary = "Edit an order")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Order updated"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun editOrder(@RequestBody order: Order): ResponseEntity<Any> {
        if (order.id == null) {
            throw IllegalArgumentException()
        }

        return ResponseEntity.ok(orderService.save(order))
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
        orderService.deleteOrderById(id)

        return ResponseEntity.noContent().build()
    }

    @GetMapping("/")
    @Operation(summary = "Get all orders")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Orders retrieved"),
            ApiResponse(responseCode = "404", description = "No orders found"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun getOrders(@RequestParam status: Status?): ResponseEntity<Any> {
        return ResponseEntity.ok(orderService.listOrders(status))
    }
}