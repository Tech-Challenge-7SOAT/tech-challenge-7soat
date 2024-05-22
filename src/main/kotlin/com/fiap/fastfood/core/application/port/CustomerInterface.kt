package com.fiap.fastfood.core.application.port

import com.fiap.fastfood.core.domain.Customer

interface CustomerInterface {

    fun fetchCustomerByCpf(cpf: String): Customer

    fun saveNewCustomer(customer: Customer): Customer

    fun fetchAllCustomers(): List<Customer>
}