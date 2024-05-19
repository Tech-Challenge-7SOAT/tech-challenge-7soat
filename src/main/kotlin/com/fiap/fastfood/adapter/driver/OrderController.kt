package com.fiap.fastfood.adapter.driver

import com.fiap.fastfood.core.application.port.service.OrderService
import com.fiap.fastfood.core.domain.Order
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController("orders")
class OrderController(private val orderService: OrderService) {
    @GetMapping("/order/{id}")
    fun getOrder(@RequestParam id: String): Order {
        return orderService.fetchOrderById(id)
    }

    @PostMapping("/order")
    fun saveOrder(@RequestBody order: Order): Order {
        return orderService.save(order)
    }

    @DeleteMapping("/order/{id}")
    fun deleteOrder(@RequestParam id: String) {
        orderService.deleteOrderById(id)
    }

    @GetMapping("/")
    fun getOrders(): List<Order> {
        return orderService.listOrders()
    }
}