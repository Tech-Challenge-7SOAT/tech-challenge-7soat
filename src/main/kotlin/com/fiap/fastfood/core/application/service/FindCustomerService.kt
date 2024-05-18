package com.fiap.fastfood.core.application.service

import arrow.core.getOrElse
import com.fiap.fastfood.core.application.port.persistence.ReadCustomerPort
import com.fiap.fastfood.core.application.usecase.FindAllCustomersUseCase
import com.fiap.fastfood.core.application.usecase.FindCustomerByCpfUseCase
import com.fiap.fastfood.core.domain.CustomerCpf
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class FindCustomerService(
    private val readCustomerPort: ReadCustomerPort): FindCustomerByCpfUseCase, FindAllCustomersUseCase {

    override fun fetchAllPersisted() = readCustomerPort.fetchAll()

    override fun findByCpf(customerCpf: CustomerCpf) = readCustomerPort.fetchByCpf(customerCpf).getOrElse { throw EntityNotFoundException() }
}