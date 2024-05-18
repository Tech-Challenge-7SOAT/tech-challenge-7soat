package com.fiap.fastfood.core.application.usecase

import com.fiap.fastfood.core.domain.Customer
import com.fiap.fastfood.core.domain.CustomerCpf

interface FindCustomerByCpfUseCase {

    fun findByCpf(customerCpf: CustomerCpf): Customer
}