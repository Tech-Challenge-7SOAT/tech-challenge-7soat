package com.fiap.fastfood.core.application.usecase

import com.fiap.fastfood.core.domain.Customer

interface CreateNewCustomerUseCase {

    fun saveCustomer(customer: Customer): Customer
}