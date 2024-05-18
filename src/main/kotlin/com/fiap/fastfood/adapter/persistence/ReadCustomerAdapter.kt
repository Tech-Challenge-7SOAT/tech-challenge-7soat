package com.fiap.fastfood.adapter.persistence

import com.fiap.fastfood.adapter.annotations.Adapter
import com.fiap.fastfood.adapter.driven.infra.CustomerRepositoryImpl
import com.fiap.fastfood.core.application.port.persistence.ReadCustomerPort
import com.fiap.fastfood.core.domain.Customer
import com.fiap.fastfood.core.domain.CustomerCpf
import com.fiap.fastfood.core.domain.CustomerFunctions.customerFirstName
import com.fiap.fastfood.core.domain.CustomerFunctions.customerLastName

@Adapter
class ReadCustomerAdapter (private val customerRepository: CustomerRepositoryImpl,
                           private val customerJpaMapper: CustomerJpaMapper): ReadCustomerPort {

    override fun existsCustomerByName(customer: Customer): Boolean {
        val firstName: String = customerFirstName(customer)
        val lastName: String = customerLastName(customer)
        return customerRepository.findByFirstNameAndLastName(firstName, lastName).isNotEmpty()
    }

    override fun existsCustomerByCpf(customerCpf: CustomerCpf): Boolean {
        val cpf: String = customerCpf.toString()
        return customerRepository.existsByCpf(cpf).isNotEmpty()
    }

    override fun fetchByCpf(customerCpf: CustomerCpf) =
        customerRepository.findByCpf(customerCpf.toString())
            .map { customerJpaMapper.toDomain(it) }
            .orElse(null)
            .toOption()

    override fun fetchAll() = customerRepository.findAll()
        .map { customerJpaMapper.toDomain(it) }
}