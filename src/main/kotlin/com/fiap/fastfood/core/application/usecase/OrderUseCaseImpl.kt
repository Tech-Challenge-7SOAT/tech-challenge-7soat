package com.fiap.fastfood.core.application.usecase

import com.fiap.fastfood.core.application.port.gateway.CustomerRepository
import com.fiap.fastfood.core.application.port.gateway.OrderRepository
import com.fiap.fastfood.core.application.port.gateway.ProductRepository
import com.fiap.fastfood.core.application.port.presenter.OrderPresenter
import com.fiap.fastfood.core.dto.OrderDTO
import com.fiap.fastfood.core.entity.CustomerEntity
import com.fiap.fastfood.core.entity.OrderEntity
import com.fiap.fastfood.core.exception.OrderNotFoundException
import com.fiap.fastfood.core.exception.OrderException
import com.fiap.fastfood.core.valueObject.Status
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderUseCaseImpl(
    private val orderRepository: OrderRepository,
    private val customerRepository: CustomerRepository,
    private val productRepository: ProductRepository,
    private val orderPresenter: OrderPresenter
) : OrderUseCase {

    override fun findOrderById(id: Long): OrderDTO {
        val orderEntity: Optional<OrderEntity> = orderRepository.findById(id)

        return orderEntity.map { orderPresenter.toDTO(it) }
            .orElseThrow { OrderNotFoundException() }
    }

    override fun save(order: OrderDTO): OrderDTO {
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

        return orderPresenter.toDTO(orderRepository.save(orderEntity))
    }

    override fun deleteOrderById(id: Long) {
        try {
            orderRepository.deleteById(id)
        } catch (e: Exception) {
            throw OrderException("Error deleting order")
        }
    }

    override fun listOrders(status: Status?): List<OrderDTO> {
        var orders: List<OrderDTO>

        if (status == null) {
            orders = orderRepository.findAll().map { orderPresenter.toDTO(it) }
        } else {
            orders = orderRepository.findByStatus(status).map { orderPresenter.toDTO(it) }
        }

        if (orders.isEmpty()) {
            throw OrderNotFoundException()
        }

        return orders
    }
}
