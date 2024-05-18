package com.fiap.fastfood.adapter.driver.controller

import com.fiap.fastfood.adapter.driver.controller.dto.CustomerDto
import com.fiap.fastfood.adapter.driver.controller.dto.SaveCustomerBodyDto
import com.fiap.fastfood.core.application.port.entrypoint.api.CreateCustomerPort
import com.fiap.fastfood.core.application.port.entrypoint.api.FindCustomerPort
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(private val changeCustomerPort: CreateCustomerPort,
                         private val findCustomerPort: FindCustomerPort
) {

    @RequestMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveCustomer(@RequestBody @Valid saveCustomerBodyDto: SaveCustomerBodyDto) =
        changeCustomerPort.saveCustomer(saveCustomerBodyDto)

    @GetMapping("/{customer_cpf}")
    @ResponseStatus(HttpStatus.OK)
    fun fetchCustomerByCpf(@PathVariable("customer_cpf") customerCpf: String ) =
        findCustomerPort.fetchCustomerByCpf(customerCpf)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun fetchAllCustomers(): Collection<CustomerDto> = findCustomerPort.fetchAllCustomers()
}