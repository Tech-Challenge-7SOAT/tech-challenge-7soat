package com.fiap.fastfood.core.application.useCase.customer

import com.fiap.fastfood.core.application.port.gateway.CustomerRepository
import com.fiap.fastfood.core.dto.customer.CustomerDTO
import com.fiap.fastfood.core.entity.CustomerEntity
import io.mockk.every
import io.mockk.mockk
import jakarta.persistence.EntityNotFoundException
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNotEmpty
import strikt.assertions.isTrue

@Profile("test")
@SpringBootTest
@AutoConfigureDataJpa
class CustomerUseCaseImplTest {

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Autowired
    private lateinit var customerUseCaseImpl: CustomerUseCaseImpl

    @BeforeEach
    fun setUp() {
        customerRepository = mockk()
        customerUseCaseImpl = CustomerUseCaseImpl(customerRepository)
    }

    @Test
    fun saveNewCustomer() {
        every { customerRepository.save(any()) } returns CustomerEntity(
            id = 1,
            firstName = "first",
            lastName = "last",
            cpf = "12345678901",
            email = "email@email.com",
            phoneNumber = "12345678901"
        )

        val customerDTO = CustomerDTO(
            id = 1L,
            firstName = "first",
            lastName = "last",
            cpf = "12345678901",
            email = "",
            phoneNumber = "12345678901"
        )

        val customerEntity = customerUseCaseImpl.saveNewCustomer(customerDTO)

        expectThat(customerEntity) {
            get { id }.isEqualTo(1)
        }
    }

    @Test
    fun existsByCpf() {
        every { customerRepository.existsByCpf("12345678901") } returns true

        val exists = customerUseCaseImpl.existsByCpf("12345678901")

        expectThat(exists).isTrue()
    }

    @Test
    fun findByCpf() {
        every { customerRepository.findByCpf("12345678901") } returns CustomerEntity(
            id = 1,
            firstName = "first",
            lastName = "last",
            cpf = "12345678901",
            email = "",
            phoneNumber = ""
        )

        val customerEntity = customerUseCaseImpl.findByCpf("12345678901")

        expectThat(customerEntity) {
            get { id }.isEqualTo(1)
        }
    }

    @Test
    fun findByCpfThrowException() {
        every { customerRepository.findByCpf(any()) } returns null

        assertThrows(EntityNotFoundException::class.java) {
            customerUseCaseImpl.findByCpf("12345678901")
        }
    }

    @Test
    fun fetchAllCustomers() {
        every { customerRepository.findAll() } returns listOf(
            CustomerEntity(
                id = 1,
                firstName = "first",
                lastName = "last",
                cpf = "12345678901",
                email = "",
                phoneNumber = ""
            )
        )

        val customers = customerUseCaseImpl.fetchAllCustomers()

        expectThat(customers).isNotEmpty()
    }

    @Test
    fun fetchAllCustomersThrowException() {
        every { customerRepository.findAll() } throws Exception()

        assertThrows(EntityNotFoundException::class.java) {
            customerUseCaseImpl.fetchAllCustomers()
        }
    }
}
