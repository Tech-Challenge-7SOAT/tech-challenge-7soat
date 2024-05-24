package com.fiap.fastfood.core.application.service

import com.fiap.fastfood.core.application.port.repository.OrderRepository
import com.fiap.fastfood.core.application.port.service.OrderService
import com.fiap.fastfood.core.domain.Order
import com.fiap.fastfood.core.entity.OrderEntity
import com.fiap.fastfood.core.exception.OrderServiceException
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrElse

@Service
class OrderServiceImpl(private val orderRepository: OrderRepository) : OrderService {
    override fun fetchOrderById(id: String): Order {
        val orderEntity: Optional<OrderEntity> = orderRepository.findById(id)

        return orderEntity.getOrElse { throw Exception("Pedido não encontrado") }.toDomain()
    }

    override fun save(order: Order): Order {
        try {
            if (order.hasCombo()) {
                return orderRepository.save(order.toEntity()).toDomain()
            }

            throw OrderServiceException("Pedido sem combo")
        } catch (e: Exception) {
            e.printStackTrace()
            throw OrderServiceException("Erro ao salvar pedido")
        }
    }

    override fun deleteOrderById(id: String) {
        try {
            orderRepository.deleteById(id)
        } catch (e: Exception) {
            throw OrderServiceException("Erro ao excluir pedido")
        }
    }

    override fun listOrders(): List<Order> {
        val orders = orderRepository.findAll().map { it.toDomain() }

        if (orders.isEmpty()) {
            throw OrderServiceException("Nenhum pedido encontrado")
        }

        return orders
    }
}