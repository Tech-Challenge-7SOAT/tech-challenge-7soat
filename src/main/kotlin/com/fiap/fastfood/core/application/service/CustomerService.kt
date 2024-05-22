package com.fiap.fastfood.core.application.service

import com.fiap.fastfood.core.application.port.CustomerInterface
import com.fiap.fastfood.core.application.port.repository.CustomerRepository
import com.fiap.fastfood.core.domain.Customer
import com.fiap.fastfood.core.entity.CustomerEntity
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrElse

@Service
class CustomerService(private val customerRepository: CustomerRepository) : CustomerInterface {
    override fun saveNewCustomer(customer: Customer): Customer {
        return customerRepository.save(customer.toEntity()).toDomain()
    }

    override fun fetchCustomerByCpf(cpf: String): Customer {
        val customerEntity: Optional<CustomerEntity> = customerRepository.findById(cpf)

        return customerEntity.getOrElse { throw Exception("Customer not found !") }.toDomain()
    }

    override fun fetchAllCustomers(): List<Customer> {
        try {
            return customerRepository.findAll().map { it.toDomain() }
        } catch (e: Exception) {
            throw EntityNotFoundException(e)
        }
    }
}