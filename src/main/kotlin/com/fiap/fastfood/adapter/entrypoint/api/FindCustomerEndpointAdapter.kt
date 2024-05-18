package com.fiap.fastfood.adapter.entrypoint.api

import com.fiap.fastfood.adapter.driver.controller.dto.CustomerDto
import com.fiap.fastfood.core.application.port.entrypoint.api.FindCustomerPort
import com.fiap.fastfood.core.application.usecase.FindAllCustomersUseCase
import com.fiap.fastfood.core.application.usecase.FindCustomerByCpfUseCase
import com.fiap.fastfood.core.domain.CustomerCpf
import org.springframework.stereotype.Component

@Component
class FindCustomerEndpointAdapter(private val findAllCustomersUseCase: FindAllCustomersUseCase,
                              private val findCustomerByCpfUseCase: FindCustomerByCpfUseCase,
                              private val customerDtoMapper: CustomerDtoMapper) : FindCustomerPort {

    override fun fetchAllCustomers() = findAllCustomersUseCase.fetchAllPersisted()
        .map { customerDtoMapper.toDto(it) }

    override fun fetchCustomerByCpf(customerCpf: String): CustomerDto {
        val cpf = CustomerCpf(customerCpf)
        val foundCustomer = findCustomerByCpfUseCase.findByCpf(cpf)
        return customerDtoMapper.toDto(foundCustomer)
    }
}