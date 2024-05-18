package com.fiap.fastfood.core.application.port.entrypoint.api

import com.fiap.fastfood.adapter.driver.controller.dto.SaveCustomerBodyDto
import com.fiap.fastfood.adapter.driver.controller.dto.CustomerDto

interface CreateCustomerPort {

    fun saveCustomer(saveCustomerDto: SaveCustomerBodyDto): CustomerDto
}