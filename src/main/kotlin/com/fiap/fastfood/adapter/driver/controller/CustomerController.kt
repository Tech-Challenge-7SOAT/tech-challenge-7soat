package com.fiap.fastfood.adapter.driver.controller

import com.fiap.fastfood.core.application.useCase.customer.CustomerUseCase
import com.fiap.fastfood.core.application.port.gateway.CustomerRepository
import com.fiap.fastfood.core.application.port.presenter.CustomerPresenter
import com.fiap.fastfood.core.dto.customer.CustomerDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@Tag(name = "Customer", description = "Create and search customer by cpf.")
@RestController
@RequestMapping("/customers")
class CustomerController(
    private val customerUseCase: CustomerUseCase,
    private val customerRepository: CustomerRepository,
    private val presenter: CustomerPresenter
) {

    @PostMapping("/customer")
    @Operation(summary = "Create a costumer")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Customer created successfully"),
        ApiResponse(responseCode = "400", description = "When the CPF already exists in database"),
        ApiResponse(responseCode = "404", description = "When can't find a customer using the CPF number"),
        ApiResponse(responseCode = "500", description = "When it isn't possible to create the customer")
    ])
    fun saveCustomer(@RequestBody @Valid customer: CustomerDTO): CustomerDTO {
        if (customerRepository.existsByCpf(customer.cpf)) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Customer with this CPF already exists."
            )
        }
        return presenter.toDTO(customerUseCase.saveNewCustomer(customer))
    }

    @GetMapping("/customer/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Returns the data of specific customer."),
        ApiResponse(responseCode = "404", description = "When don't find a customer using the CPF number specific."),
        ApiResponse(responseCode = "500", description = "When it is not possible to find the customer.")
    ])
    fun fetchCustomerByCpf(@PathVariable("cpf") cpf: String ) : CustomerDTO? {
        if (!customerRepository.existsByCpf(cpf)) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "Customer with this CPF does not exist."
            )
        }
        return presenter.toDTO(customerUseCase.findByCpf(cpf))
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Returns a list with all customers of database."),
        ApiResponse(responseCode = "500", description = "When it is not possible to find a list of customers.")
    ])
    fun fetchAllCustomers(): Collection<CustomerDTO> {
        val customers = customerUseCase.fetchAllCustomers()
        return customers.map { customer -> presenter.toDTO(customer) }
    }
}