package com.fiap.fastfood.core.application.port.presenter

import com.fiap.fastfood.core.dto.CustomerDTO
import com.fiap.fastfood.core.entity.CustomerEntity
import org.springframework.stereotype.Component

@Component
class CustomerPresenter {

    fun toDTO(customerEntity: CustomerEntity): CustomerDTO {
        return CustomerDTO(
            id = customerEntity.id,
            firstName = customerEntity.firstName,
            lastName = customerEntity.lastName,
            cpf = customerEntity.cpf,
            email = customerEntity.email,
            phoneNumber = customerEntity.phoneNumber,
            createdAt = customerEntity.createdAt
        )
    }
}