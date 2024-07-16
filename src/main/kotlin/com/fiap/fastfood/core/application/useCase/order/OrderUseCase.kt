package com.fiap.fastfood.core.application.useCase.order

import com.fiap.fastfood.core.dto.order.OrderRequestCreateDTO
import com.fiap.fastfood.core.dto.order.OrderRequestUpdateDTO
import com.fiap.fastfood.core.entity.OrderEntity
import com.fiap.fastfood.core.valueObject.Status

interface OrderUseCase {
    fun findOrderById(id: Long): OrderEntity
    fun create(order: OrderRequestCreateDTO): OrderEntity
    fun deleteOrderById(id: Long)
    fun listOrders(status: Status?): List<OrderEntity>
    fun update(order: OrderRequestUpdateDTO): OrderEntity
}