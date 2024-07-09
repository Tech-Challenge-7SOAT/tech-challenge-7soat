package com.fiap.fastfood.core.application.usecase

import com.fiap.fastfood.core.dto.OrderDTO
import com.fiap.fastfood.core.valueObject.Status

interface OrderUseCase {
    fun findOrderById(id: Long): OrderDTO
    fun save(order: OrderDTO): OrderDTO
    fun deleteOrderById(id: Long)
    fun listOrders(status: Status?): List<OrderDTO>
}