package com.fiap.fastfood.adapter.driver.controller

import com.fiap.fastfood.core.application.port.CustomerInterface
import com.fiap.fastfood.core.domain.Customer
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "Customer", description = "Create and search customer by cpf.")
@RestController
@RequestMapping("/customers")
class CustomerController(private val customerInterface: CustomerInterface) {

    @PostMapping("/customer")
    @Operation(summary = "Create a costumer")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveCustomer(@RequestBody @Valid customer: Customer): Customer {
        return customerInterface.saveNewCustomer(customer)
    }

    @GetMapping("/customer/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    fun fetchCustomerByCpf(@PathVariable("cpf") cpf: String ) =
        customerInterface.findByCpf(cpf)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun fetchAllCustomers(): Collection<Customer> = customerInterface.fetchAllCustomers()
}