package com.fiap.fastfood.adapter.entrypoint.api

import com.fiap.fastfood.adapter.driver.controller.dto.CustomerDto
import com.fiap.fastfood.adapter.driver.controller.dto.SaveCustomerBodyDto
import com.fiap.fastfood.core.application.port.entrypoint.api.CreateCustomerPort
import com.fiap.fastfood.core.application.usecase.CreateNewCustomerUseCase
import org.springframework.stereotype.Component

@Component
class CreateCustomerEndpointAdapter(
    private val createNewCustomerUseCase: CreateNewCustomerUseCase,
    private val customerDtoMapper: CustomerDtoMapper): CreateCustomerPort {

    override fun saveCustomer(saveCustomerBodyDto: SaveCustomerBodyDto): CustomerDto {
        val customer        = customerDtoMapper.toDomainFromSaveBody(saveCustomerBodyDto)
        val savedCustomer   = createNewCustomerUseCase.saveCustomer(customer)
        return customerDtoMapper.toDto(savedCustomer)
    }
}