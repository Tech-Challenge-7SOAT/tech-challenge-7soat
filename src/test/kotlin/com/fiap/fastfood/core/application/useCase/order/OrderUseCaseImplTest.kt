package com.fiap.fastfood.core.application.useCase.order

import com.fiap.fastfood.core.application.port.gateway.CustomerRepository
import com.fiap.fastfood.core.application.port.gateway.OrderRepository
import com.fiap.fastfood.core.application.port.gateway.ProductRepository
import com.fiap.fastfood.core.dto.order.OrderRequestCreateDTO
import com.fiap.fastfood.core.dto.order.OrderRequestUpdateDTO
import com.fiap.fastfood.core.entity.CustomerEntity
import com.fiap.fastfood.core.entity.OrderEntity
import com.fiap.fastfood.core.entity.ProductEntity
import com.fiap.fastfood.core.exception.OrderNotFoundException
import com.fiap.fastfood.core.valueObject.ProductCategory
import com.fiap.fastfood.core.valueObject.Status
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Profile("test")
@SpringBootTest
@AutoConfigureDataJpa
class OrderUseCaseImplTest {

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var orderUseCaseImpl: OrderUseCaseImpl

    @BeforeEach
    fun setup() {
        orderRepository = mockk()
        customerRepository = mockk()
        productRepository = mockk()
        orderUseCaseImpl = OrderUseCaseImpl(orderRepository, customerRepository, productRepository)
    }

    @Test
    fun findOrderByIdThrowException() {
        every { orderRepository.findById(any()) } returns Optional.empty()

        assertThrows<OrderNotFoundException> {
            orderUseCaseImpl.findOrderById(1)
        }
    }

    @Test
    fun findOrderById() {
        every { orderRepository.findById(any()) } returns Optional.of(OrderEntity())

        assertDoesNotThrow {
            orderUseCaseImpl.findOrderById(1)
        }
    }

    @Test
    fun create() {
        every { customerRepository.findByCpf(any()) } returns CustomerEntity(
            id = 1,
            firstName = "first",
            lastName = "last",
            cpf = "12345678901",
            email = "email@email.com",
            phoneNumber = "12345678901"
        )

        every { productRepository.findAllByIdIn(any()) } returns listOf(
            ProductEntity(
                id = 1,
                name = "Test",
                category = ProductCategory.LANCHE,
                description = "Test",
                price = 1.0,
                timeToPrepare = 1
            )
        )

        every { orderRepository.save(any()) } returns OrderEntity()

        assertDoesNotThrow { orderUseCaseImpl.create(OrderRequestCreateDTO(cpf = "cpf", emptyList())) }
    }

    @Test
    fun update() {
        every { customerRepository.findByCpf(any()) } returns CustomerEntity(
            id = 1,
            firstName = "first",
            lastName = "last",
            cpf = "12345678901",
            email = "email@email.com",
            phoneNumber = "12345678901"
        )

        every { orderRepository.findById(any()) } returns Optional.of(OrderEntity())

        every { productRepository.findAllByIdIn(any()) } returns listOf(
            ProductEntity(
                id = 1,
                name = "Test",
                category = ProductCategory.LANCHE,
                description = "Test",
                price = 1.0,
                timeToPrepare = 1
            )
        )

        every { orderRepository.save(any()) } returns OrderEntity()

        assertDoesNotThrow {
            orderUseCaseImpl.update(
                OrderRequestUpdateDTO(
                    cpf = "cpf",
                    id = 1L,
                    status = Status.PENDENTE,
                    productIds = emptyList()
                )
            )
        }
    }

    @Test
    fun deleteOrderById() {
        every { orderRepository.deleteById(any()) } returns Unit

        assertDoesNotThrow {
            orderUseCaseImpl.deleteOrderById(1)
        }
    }

    @Test
    fun listOrders() {
        every { orderRepository.findAll() } returns listOf(OrderEntity())

        assertDoesNotThrow {
            orderUseCaseImpl.listOrders(Status.PENDENTE)
        }
    }

    @Test
    fun listOrdersEmpty() {
        every { orderRepository.findAll() } returns emptyList()

        assertThrows<OrderNotFoundException> {
            orderUseCaseImpl.listOrders(Status.PENDENTE)
        }
    }

    @Test
    fun updateStatus() {
        every { orderRepository.findById(any()) } returns Optional.of(OrderEntity())
        every { orderRepository.save(any()) } returns OrderEntity()

        assertDoesNotThrow {
            orderUseCaseImpl.updateStatus(1, Status.EM_PREPARACAO)
        }
    }

    @Test
    fun updateStatusThrowException() {
        every { orderRepository.findById(any()) } returns Optional.empty()

        assertThrows<OrderNotFoundException> {
            orderUseCaseImpl.updateStatus(1, Status.EM_PREPARACAO)
        }
    }
}
