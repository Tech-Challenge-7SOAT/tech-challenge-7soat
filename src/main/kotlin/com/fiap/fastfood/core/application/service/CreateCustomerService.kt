package com.fiap.fastfood.core.application.service

import com.fiap.fastfood.core.application.port.persistence.ReadCustomerPort
import com.fiap.fastfood.core.application.port.persistence.WriteCustomerPort
import com.fiap.fastfood.core.application.usecase.CreateNewCustomerUseCase
import com.fiap.fastfood.core.domain.Customer
import org.springframework.stereotype.Service

@Service
class CreateCustomerService(
    private val readCustomerPort: ReadCustomerPort,
    private val writeCustomerPort: WriteCustomerPort
): CreateNewCustomerUseCase{

    override fun saveCustomer(customer: Customer): Customer {
        require(!readCustomerPort.existsCustomerByName(customer)) { "Customer duplicated..." }
        return writeCustomerPort.saveNew(customer)
    }
}