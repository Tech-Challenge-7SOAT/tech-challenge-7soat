package com.fiap.fastfood.core.application.usecase

import com.fiap.fastfood.core.dto.CustomerDTO

interface CustomerUseCase {

    fun saveNewCustomer(customer: CustomerDTO): CustomerDTO

    fun existsByCpf(cpf: String): Boolean

    fun findByCpf(cpf: String): CustomerDTO

    fun fetchAllCustomers(): List<CustomerDTO>
}