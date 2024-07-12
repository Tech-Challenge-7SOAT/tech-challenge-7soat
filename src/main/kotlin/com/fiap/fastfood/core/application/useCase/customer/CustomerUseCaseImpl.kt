package com.fiap.fastfood.core.application.useCase.customer

import com.fiap.fastfood.core.application.port.gateway.CustomerRepository
import com.fiap.fastfood.core.dto.CustomerDTO
import com.fiap.fastfood.core.entity.CustomerEntity
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomerUseCaseImpl(
    private val customerRepository: CustomerRepository
) : CustomerUseCase {

    override fun saveNewCustomer(customer: CustomerDTO): CustomerEntity {
        return customerRepository.save(customer.toEntity())
    }

    override fun existsByCpf(cpf: String): Boolean {
        return customerRepository.existsByCpf(cpf)
    }

    override fun findByCpf(cpf: String): CustomerEntity {
        try {
            return customerRepository.findByCpf(cpf)!!
        } catch(e: EntityNotFoundException) {
            throw EntityNotFoundException(e)
        }
    }

    override fun fetchAllCustomers(): List<CustomerEntity> {
        try {
            return customerRepository.findAll().map { it }
        } catch (e: Exception) {
            throw EntityNotFoundException(e)
        }
    }
}