package com.fiap.fastfood.adapter.persistence

import com.fiap.fastfood.adapter.annotations.Mapper
import com.fiap.fastfood.adapter.driven.infra.data.CustomerData
import com.fiap.fastfood.core.domain.*
import com.fiap.fastfood.core.domain.CustomerFunctions.customerCpf
import com.fiap.fastfood.core.domain.CustomerFunctions.customerEmail
import com.fiap.fastfood.core.domain.CustomerFunctions.customerFirstName
import com.fiap.fastfood.core.domain.CustomerFunctions.customerId
import com.fiap.fastfood.core.domain.CustomerFunctions.customerLastName
import com.fiap.fastfood.core.domain.CustomerFunctions.customerPhoneNumber
import java.time.LocalDateTime

@Mapper
class CustomerJpaMapper {

    fun toJpaEntity(customer: Customer) = CustomerData.Builder()
        .id(customerId(customer))
        .firstName(customerFirstName(customer))
        .lastName(customerLastName(customer))
        .cpf(customerCpf(customer))
        .email(customerEmail(customer))
        .phoneNumber(customerPhoneNumber(customer))
        .createdAt(LocalDateTime.now())
        .build()

    fun toDomain(customerData: CustomerData) = Customer.Builder()
        .id(CustomerId(customerData.id!!))
        .fullName(CustomerFullName(customerData.firstName!!, customerData.lastName!!))
        .cpf(CustomerCpf(customerData.cpf!!))
        .email(CustomerEmail(customerData.email!!))
        .phoneNumber(CustomerPhoneNumber(customerData.phoneNumber!!))
        .build()
}