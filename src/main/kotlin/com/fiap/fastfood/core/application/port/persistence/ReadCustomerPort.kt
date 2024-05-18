package com.fiap.fastfood.core.application.port.persistence

import arrow.core.Option
import com.fiap.fastfood.core.domain.Customer
import com.fiap.fastfood.core.domain.CustomerCpf

interface ReadCustomerPort {

    fun existsCustomerByName(customer: Customer): Boolean

    fun existsCustomerByCpf(customerCpf: CustomerCpf): Boolean

    fun fetchByCpf(customerCpf: CustomerCpf): Option<Customer>

    fun fetchAll(): List<Customer>
}