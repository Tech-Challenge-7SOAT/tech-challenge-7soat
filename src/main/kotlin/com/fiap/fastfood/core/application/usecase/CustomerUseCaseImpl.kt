package com.fiap.fastfood.core.application.usecase

import com.fiap.fastfood.core.application.port.gateway.CustomerRepository
import com.fiap.fastfood.core.application.port.presenter.CustomerPresenter
import com.fiap.fastfood.core.dto.CustomerDTO
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomerUseCaseImpl(
    private val customerRepository: CustomerRepository, 
    private val customerPresenter: CustomerPresenter
) : CustomerUseCase {

    override fun saveNewCustomer(customer: CustomerDTO): CustomerDTO {
        return customerPresenter.toDTO(customerRepository.save(customer.toEntity()))
    }

    override fun existsByCpf(cpf: String): Boolean {
        return customerRepository.existsByCpf(cpf)
    }

    override fun findByCpf(cpf: String): CustomerDTO {
        try {
            val customer = customerRepository.findByCpf(cpf)
            return customerPresenter.toDTO(customer!!)
        } catch(e: EntityNotFoundException) {
            throw EntityNotFoundException(e)
        }
    }

    override fun fetchAllCustomers(): List<CustomerDTO> {
        try {
            return customerRepository.findAll().map { customerPresenter.toDTO(it) }
        } catch (e: Exception) {
            throw EntityNotFoundException(e)
        }
    }
}