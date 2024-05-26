package com.fiap.fastfood.core.application.port

import com.fiap.fastfood.core.domain.Customer

interface CustomerInterface {

    fun saveNewCustomer(customer: Customer): Customer

    fun existsByCpf(cpf: String): Boolean

    fun findByCpf(cpf: String): Customer

    fun fetchAllCustomers(): List<Customer>
}