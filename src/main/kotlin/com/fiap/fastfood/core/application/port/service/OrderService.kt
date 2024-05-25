package com.fiap.fastfood.core.application.port.service

import com.fiap.fastfood.core.domain.Order
import com.fiap.fastfood.core.valueObject.Status

interface OrderService {
    fun findOrderById(id: Long): Order
    fun save(order: Order): Order
    fun deleteOrderById(id: Long)
    fun listOrders(status: Status?): List<Order>
}