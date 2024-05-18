package com.fiap.fastfood.core.application.port.entrypoint.api

import com.fiap.fastfood.adapter.driver.controller.dto.CustomerDto

interface FindCustomerPort {

    fun fetchAllCustomers(): Collection<CustomerDto>

    fun fetchCustomerByCpf(customerCpf: String): CustomerDto
}