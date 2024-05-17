package com.fiap.fastfood.core.application.port.repository

import com.fiap.fastfood.core.entity.OrderEntity
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<OrderEntity, String> {
}