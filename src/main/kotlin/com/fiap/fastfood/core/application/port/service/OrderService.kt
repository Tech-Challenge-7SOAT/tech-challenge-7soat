package com.fiap.fastfood.core.application.port.service

import com.fiap.fastfood.core.domain.Order

interface OrderService {
   fun fetchOrderById(id: String): Order
   fun save(order: Order): Order
   fun deleteOrderById(id: String)
   fun listOrders(): List<Order>
}