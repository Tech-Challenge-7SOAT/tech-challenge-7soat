package com.fiap.fastfood.core.application.port.presenter

import com.fiap.fastfood.core.dto.order.OrderDTO
import com.fiap.fastfood.core.entity.OrderEntity
import org.springframework.stereotype.Component

@Component
class OrderPresenter(
    private val oderProductPresenter: OrderProductPresenter,
    private val customerPresenter: CustomerPresenter
) {

    fun toDTO(orderEntity: OrderEntity): OrderDTO {
        return OrderDTO(
            orderEntity.id,
            orderEntity.customer?.let { customerPresenter.toDTO(it) },
            orderEntity.isPayed,
            orderEntity.status,
            orderEntity.products.map { oderProductPresenter.toDTO(it) },
            orderEntity.createdAt,
            orderEntity.updatedAt)
    }
}