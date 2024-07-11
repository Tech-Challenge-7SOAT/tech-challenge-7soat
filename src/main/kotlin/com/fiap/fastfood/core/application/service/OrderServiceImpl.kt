package com.fiap.fastfood.core.application.service

import com.fiap.fastfood.core.application.port.repository.CustomerRepository
import com.fiap.fastfood.core.application.port.repository.OrderRepository
import com.fiap.fastfood.core.application.port.repository.ProductRepository
import com.fiap.fastfood.core.application.port.service.OrderService
import com.fiap.fastfood.core.domain.Customer
import com.fiap.fastfood.core.domain.Order
import com.fiap.fastfood.core.entity.CustomerEntity
import com.fiap.fastfood.core.entity.OrderEntity
import com.fiap.fastfood.core.entity.OrderStatusEntity
import com.fiap.fastfood.core.exception.OrderNotFoundException
import com.fiap.fastfood.core.exception.OrderServiceException
import com.fiap.fastfood.core.valueObject.Status
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrElse
import kotlin.jvm.optionals.getOrNull

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val customerRepository: CustomerRepository,
    private val productRepository: ProductRepository
) : OrderService {

    override fun findOrderById(id: Long): Order {
        val orderEntity: Optional<OrderEntity> = orderRepository.findById(id)

        return orderEntity.getOrElse { throw OrderNotFoundException() }.toDomain()
    }

    override fun save(order: Order): Order {
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

        return orderRepository.save(orderEntity).toDomain()
    }

    override fun deleteOrderById(id: Long) {
        try {
            orderRepository.deleteById(id)
        } catch (e: Exception) {
            throw OrderServiceException("Error deleting order")
        }
    }

    override fun listOrders(status: Status?): List<Order> {
        var orders: List<Order>

        if (status == null) {
            orders = orderRepository.findAll().map { it.toDomain() }
        } else {
            orders = orderRepository.findByStatus(status).map { it.toDomain() }
        }

        if (orders.isEmpty()) {
            throw OrderNotFoundException()
        }

        return orders
    }

    override fun findOrderStatusById(id: Long): OrderStatusEntity? =
        orderRepository.findById(id)
            .getOrElse { throw OrderNotFoundException() }
            ?.let { OrderStatusEntity(it.id!!, it.status) }
}
