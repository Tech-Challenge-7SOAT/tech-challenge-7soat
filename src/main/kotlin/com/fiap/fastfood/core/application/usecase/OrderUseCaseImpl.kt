package com.fiap.fastfood.core.application.usecase

import com.fiap.fastfood.core.application.port.gateway.CustomerRepository
import com.fiap.fastfood.core.application.port.gateway.OrderRepository
import com.fiap.fastfood.core.application.port.gateway.ProductRepository
import com.fiap.fastfood.core.dto.OrderDTO
import com.fiap.fastfood.core.entity.CustomerEntity
import com.fiap.fastfood.core.entity.OrderEntity
import com.fiap.fastfood.core.exception.OrderNotFoundException
import com.fiap.fastfood.core.exception.OrderException
import com.fiap.fastfood.core.valueObject.Status
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrElse

@Service
class OrderUseCaseImpl(
    private val orderRepository: OrderRepository,
    private val customerRepository: CustomerRepository,
    private val productRepository: ProductRepository
) : OrderUseCase {

    override fun findOrderById(id: Long): OrderEntity {
        val orderEntity: Optional<OrderEntity> = orderRepository.findById(id)

        return orderEntity.getOrElse { throw OrderNotFoundException() }
    }

    override fun save(order: OrderDTO): OrderEntity {
        val customerEntity: CustomerEntity = customerRepository.save(order.customer!!.toEntity())
        val products = productRepository.findAllByIdIn(order.products.map { it.id!! })

        val orderEntity = OrderEntity(
            order.id,
            order.totalAmount,
            customerEntity,
            order.isPayed,
            order.status,
            products.toMutableList(),
            order.createdAt,
            order.updatedAt
        )

        return orderRepository.save(orderEntity)
    }

    override fun deleteOrderById(id: Long) {
        try {
            orderRepository.deleteById(id)
        } catch (e: Exception) {
            throw OrderException("Error deleting order")
        }
    }

    override fun listOrders(status: Status?): List<OrderEntity> {
        var orders: List<OrderEntity>

        if (status == null) {
            orders = orderRepository.findAll().map { it }
        } else {
            orders = orderRepository.findByStatus(status).map { it }
        }

        if (orders.isEmpty()) {
            throw OrderNotFoundException()
        }

        return orders
    }
}
