package com.fiap.fastfood.core.application.port.presenter

import com.fiap.fastfood.core.entity.CustomerEntity
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CustomerPresenterTest {

    @Autowired
    private lateinit var presenter: CustomerPresenter

    @Test
    fun toDTO() {
        val customerEntity = CustomerEntity(
            id = 1,
            firstName = "John",
            lastName = "Doe",
            cpf = "123.456.789-00",
            email = "",
            phoneNumber = ""
        )

        val customerDTO = presenter.toDTO(customerEntity)

        assertEquals(customerEntity.id, customerDTO.id)
    }
}
