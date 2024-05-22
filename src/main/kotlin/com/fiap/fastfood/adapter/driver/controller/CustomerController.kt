package com.fiap.fastfood.adapter.driver.controller

import com.fiap.fastfood.core.application.port.CustomerInterface
import com.fiap.fastfood.core.domain.Customer
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(private val customerInterface: CustomerInterface) {

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveCustomer(@RequestBody @Valid customer: Customer): Customer {
        return customerInterface.saveNewCustomer(customer)
    }

    @GetMapping("/{customer_cpf}")
    @ResponseStatus(HttpStatus.OK)
    fun fetchCustomerByCpf(@PathVariable("customer_cpf") cpf: String ) =
        customerInterface.fetchCustomerByCpf(cpf)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun fetchAllCustomers(): Collection<Customer> = customerInterface.fetchAllCustomers()
}