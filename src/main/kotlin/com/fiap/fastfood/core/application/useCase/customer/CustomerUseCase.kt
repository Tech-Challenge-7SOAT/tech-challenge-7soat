package com.fiap.fastfood.core.application.useCase.customer

import com.fiap.fastfood.core.dto.customer.CustomerDTO
import com.fiap.fastfood.core.entity.CustomerEntity

interface CustomerUseCase {

    fun saveNewCustomer(customer: CustomerDTO): CustomerEntity

    fun existsByCpf(cpf: String): Boolean

    fun findByCpf(cpf: String): CustomerEntity

    fun fetchAllCustomers(): List<CustomerEntity>
}