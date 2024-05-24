package com.fiap.fastfood.adapter.driver

import com.fiap.fastfood.core.application.port.service.OrderService
import com.fiap.fastfood.core.domain.Order
import com.fiap.fastfood.core.exception.OrderServiceException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(private val orderService: OrderService) {
    @GetMapping("/order/{id}")
    fun getOrder(@PathVariable id: String): Order {
        return orderService.fetchOrderById(id)
    }

    @PostMapping("/order")
    fun saveOrder(@RequestBody order: Order): Order {
        return orderService.save(order)
    }

    @DeleteMapping("/order/{id}")
    fun deleteOrder(@PathVariable id: String) {
        orderService.deleteOrderById(id)
    }

    @GetMapping("/")
    fun getOrders(): ResponseEntity<Any> {
        try {
            val orders = orderService.listOrders()
            println(orders.size)
            return ResponseEntity.status(HttpStatus.OK).body(orders)
        } catch (orderServiceException: OrderServiceException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(orderServiceException.message)
        }
    }
}