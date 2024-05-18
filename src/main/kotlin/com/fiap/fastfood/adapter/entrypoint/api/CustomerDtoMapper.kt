package com.fiap.fastfood.adapter.entrypoint.api

import com.fiap.fastfood.adapter.annotations.Mapper
import com.fiap.fastfood.adapter.driver.controller.dto.CustomerDto
import com.fiap.fastfood.adapter.driver.controller.dto.SaveCustomerBodyDto
import com.fiap.fastfood.core.domain.*
import com.fiap.fastfood.core.domain.Customer.*
import com.fiap.fastfood.core.domain.CustomerFunctions.customerCpf
import com.fiap.fastfood.core.domain.CustomerFunctions.customerEmail
import com.fiap.fastfood.core.domain.CustomerFunctions.customerFirstName
import com.fiap.fastfood.core.domain.CustomerFunctions.customerLastName
import com.fiap.fastfood.core.domain.CustomerFunctions.customerPhoneNumber

@Mapper
class CustomerDtoMapper {

    fun toDto(customer: Customer): CustomerDto {
        return CustomerDto.Builder()
                .firstName(customerFirstName(customer))
                .lastName(customerLastName(customer))
                .cpf(customerCpf(customer))
                .email(customerEmail(customer))
                .phoneNumber(customerPhoneNumber(customer))
                .build()
    }

    fun toDomainFromSaveBody(saveCustomerBodyDto: SaveCustomerBodyDto): Customer {
        return Builder()
                .fullName(CustomerFullName(saveCustomerBodyDto.firstName, saveCustomerBodyDto.lastName))
                .cpf(CustomerCpf(saveCustomerBodyDto.cpf))
                .email(CustomerEmail(saveCustomerBodyDto.email))
                .phoneNumber(CustomerPhoneNumber(saveCustomerBodyDto.phoneNumber))
                .build()
    }

    fun toDomainFromSaveBody(customerId: Int, saveCustomerBodyDto: SaveCustomerBodyDto): Customer {
        return Builder()
                .id(CustomerId(customerId))
                .fullName(CustomerFullName(saveCustomerBodyDto.firstName, saveCustomerBodyDto.lastName))
                .cpf(CustomerCpf(saveCustomerBodyDto.cpf))
                .email(CustomerEmail(saveCustomerBodyDto.email))
                .phoneNumber(CustomerPhoneNumber(saveCustomerBodyDto.phoneNumber))
                .build()
    }
}