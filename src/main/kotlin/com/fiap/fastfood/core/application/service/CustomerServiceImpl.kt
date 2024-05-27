package com.fiap.fastfood.core.application.service

import com.fiap.fastfood.core.application.port.service.CustomerService
import com.fiap.fastfood.core.application.port.repository.CustomerRepository
import com.fiap.fastfood.core.domain.Customer
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(private val customerRepository: CustomerRepository) : CustomerService {

    override fun saveNewCustomer(customer: Customer): Customer {
        return customerRepository.save(customer.toEntity()).toDomain()
    }

    override fun existsByCpf(cpf: String): Boolean {
        return customerRepository.existsByCpf(cpf)
    }

    override fun findByCpf(cpf: String): Customer {
        try {
            return customerRepository.findByCpf(cpf)!!.toDomain()
        } catch(e: EntityNotFoundException) {
            throw EntityNotFoundException(e)
        }
    }

    override fun fetchAllCustomers(): List<Customer> {
        try {
            return customerRepository.findAll().map { it.toDomain() }
        } catch (e: Exception) {
            throw EntityNotFoundException(e)
        }
    }
}