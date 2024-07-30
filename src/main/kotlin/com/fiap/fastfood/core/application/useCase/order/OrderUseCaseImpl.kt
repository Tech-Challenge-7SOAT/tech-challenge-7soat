package com.fiap.fastfood.core.application.useCase.order

import com.fiap.fastfood.core.application.port.gateway.CustomerRepository
import com.fiap.fastfood.core.application.port.gateway.OrderRepository
import com.fiap.fastfood.core.application.port.gateway.ProductRepository
import com.fiap.fastfood.core.dto.order.OrderRequestCreateDTO
import com.fiap.fastfood.core.dto.order.OrderRequestUpdateDTO
import com.fiap.fastfood.core.dto.order.ProductsItens
import com.fiap.fastfood.core.entity.CustomerEntity
import com.fiap.fastfood.core.entity.OrderEntity
import com.fiap.fastfood.core.entity.OrderProductEntity
import com.fiap.fastfood.core.entity.ProductEntity
import com.fiap.fastfood.core.exception.OrderException
import com.fiap.fastfood.core.exception.OrderNotFoundException
import com.fiap.fastfood.core.valueObject.Status
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
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

    override fun create(order: OrderRequestCreateDTO): OrderEntity {
        validateCpf(order.cpf)
        val customerEntity = findCustomerByCpf(order.cpf)
        val products = findProductsByIds(order.productIds.map { it.id })

        val orderEntity = OrderEntity(
            null,
            products.sumOf { it.price },
            products.sumOf { it.timeToPrepare },
            customerEntity,
            false,
            Status.PENDENTE,
            mutableListOf(),
            null,
            null
        )

        orderEntity.products = createOrderProducts(products, order.productIds, orderEntity)
        return orderRepository.save(orderEntity)
    }

    override fun update(order: OrderRequestUpdateDTO): OrderEntity {
        if (order.cpf.isNullOrEmpty())
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF is required for updating an order")
        val orderEntity = orderRepository.findById(order.id).orElseThrow { OrderNotFoundException() }
        validateOrderStatus(orderEntity.status)
        val customerEntity = findCustomerByCpf(order.cpf)
        var products = order.productIds?.let { findProductsByIds(it.map { it.id }) }

        orderEntity.apply {
            totalAmount = products?.sumOf { it.price } ?: totalAmount
            timeToPrepare = products?.sumOf { it.timeToPrepare } ?: timeToPrepare
            customer = customerEntity
            isPayed = status != Status.PENDENTE
            status = order.status?.let { it } ?: status
            if (products != null && order.productIds != null) {
                val orderProducts = createOrderProducts(products, order.productIds, this)
                this.orderProducts.clear()
                this.orderProducts.addAll(orderProducts)
            }
        }
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
        val orders = sortOrders(orderRepository.findAll().map { it }, status)

        if (orders.isEmpty())
            throw OrderNotFoundException()

        return orders
    }

    private fun sortOrders(orders: List<OrderEntity>, status: Status?): List<OrderEntity> {
        try {
            return when (status) {
                null -> orders.filterNot { it.status == Status.FINALIZADO }
                    .sortedWith(compareBy({ it.status.ordinal }, { it.createdAt }))

                else -> orders.sortedWith(compareBy { it.createdAt })
            }
        } catch (e: Exception) {
            throw OrderException("Error sorting orders")
        }
    }

    private fun validateProducts(requestedProductIds: List<Long>, foundProducts: List<ProductEntity>) {
        val foundProductIds = foundProducts.map { it.id }
        val notFoundProductIds = requestedProductIds - foundProductIds

        if (notFoundProductIds.isNotEmpty())
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found for IDs: $notFoundProductIds")
    }

    private fun findCustomerByCpf(cpf: String): CustomerEntity {
        return customerRepository.findByCpf(cpf)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with this CPF does not exist.")
    }

    private fun findProductsByIds(productsIds: List<Long>): List<ProductEntity> {
        val productsFoundByIds = productRepository.findAllByIdIn(productsIds)
        validateProducts(productsIds, productsFoundByIds)
        return productsFoundByIds
    }

    private fun createOrderProducts(
        products: List<ProductEntity>,
        productItems: List<ProductsItens>?,
        order: OrderEntity
    ): MutableList<OrderProductEntity> {
        return productItems?.map { productItem ->
            val product = products.find { it.id == productItem.id }
            if (product != null) {
                OrderProductEntity(
                    product = product,
                    quantity = productItem.quantity,
                    order = order
                )
            } else {
                throw OrderException("Error creating order products")
            }
        }?.toMutableList() ?: mutableListOf()
    }

    override fun updateStatus(id: Long, status: Status): Boolean {
        val order = orderRepository.findById(id).orElseThrow { OrderNotFoundException() }
        if (order.hasFinished()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Order already finalized")
        }

        with(order) {
            this.status = status
            orderRepository.save(this)
        }

        return true
    }

    private fun validateCpf(cpf: String?) {
        if (cpf.isNullOrEmpty())
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF is required")
    }

    private fun validateOrderStatus(status: Status) {
        if (status == Status.FINALIZADO || status == Status.PAGAMENTO_RECUSADO)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Order already finalized")
    }
}
