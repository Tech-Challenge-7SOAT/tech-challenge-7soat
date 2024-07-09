package com.fiap.fastfood.core.application.usecase

import com.fiap.fastfood.core.dto.OrderDTO
import com.fiap.fastfood.core.entity.OrderEntity
import com.fiap.fastfood.core.valueObject.Status

interface OrderUseCase {
    fun findOrderById(id: Long): OrderEntity
    fun save(order: OrderDTO): OrderEntity
    fun deleteOrderById(id: Long)
    fun listOrders(status: Status?): List<OrderEntity>
}