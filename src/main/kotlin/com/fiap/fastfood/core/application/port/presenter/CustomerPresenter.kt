package com.fiap.fastfood.core.application.port.presenter

import com.fiap.fastfood.core.dto.CustomerDTO
import com.fiap.fastfood.core.entity.CustomerEntity

class CustomerPresenter(private val customer: CustomerDTO) {

    fun toDTO(customerEntity: CustomerEntity): CustomerDTO {
        return CustomerDTO(
            id = customer.id,
            firstName = customer.firstName,
            lastName = customer.lastName,
            cpf = customer.cpf,
            email = customer.email,
            phoneNumber = customer.phoneNumber,
            createdAt = customer.createdAt
        )
    }
}