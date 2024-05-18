package com.fiap.fastfood.core.application.port.persistence

import com.fiap.fastfood.core.domain.Customer

interface WriteCustomerPort {

    fun saveNew(customer: Customer): Customer
}