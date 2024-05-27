package com.fiap.fastfood.core.application.service

import com.fiap.fastfood.core.application.port.repository.CustomerRepository
import com.fiap.fastfood.core.application.port.repository.OrderRepository
import com.fiap.fastfood.core.application.port.repository.ProductRepository
import com.fiap.fastfood.core.application.port.service.OrderService
import com.fiap.fastfood.core.domain.Customer
import com.fiap.fastfood.core.domain.Order
import com.fiap.fastfood.core.entity.CustomerEntity
import com.fiap.fastfood.core.entity.OrderEntity
import com.fiap.fastfood.core.exception.OrderNotFoundException
import com.fiap.fastfood.core.exception.OrderServiceException
import com.fiap.fastfood.core.valueObject.Status
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrElse

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository, private val customerRepository: CustomerRepository, private val productRepository: ProductRepository
) : OrderService {
    override fun findOrderById(id: Long): Order {
        val orderEntity: Optional<OrderEntity> = orderRepository.findById(id)

        return orderEntity.getOrElse { throw OrderNotFoundException() }.toDomain()
    }

    override fun save(order: Order): Order {
        try {
            if (order.hasCombo()) {
                val orderEntity = order.toEntity()

                orderEntity.customer = if (order.customer != null) customerRepository.findById(order.customer.id)
                    .getOrElse { throw OrderServiceException("Customer not found") }
                else CustomerEntity(-1L, "", "", "", "", "")

                orderEntity.products = productRepository.findAllByIdIn(order.products.map { it.id!! })

                return orderRepository.save(order.toEntity()).toDomain()
            }

            throw OrderServiceException("Order without combo")
        } catch (e: Exception) {
            e.printStackTrace()
            throw OrderServiceException("Error saving order")
        }
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
}