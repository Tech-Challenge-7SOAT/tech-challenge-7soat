package com.fiap.fastfood.adapter.persistence

import com.fiap.fastfood.adapter.annotations.Adapter
import com.fiap.fastfood.adapter.driven.infra.CustomerRepositoryImpl
import com.fiap.fastfood.core.application.port.persistence.WriteCustomerPort
import com.fiap.fastfood.core.domain.Customer

@Adapter
class WriteCustomerAdapter(private val customerRepository: CustomerRepositoryImpl,
                       private val customerJpaMapper: CustomerJpaMapper): WriteCustomerPort {

    override fun saveNew(customer: Customer): Customer {
        val customerData = customerJpaMapper.toJpaEntity(customer)
        val customerSaved = customerRepository.save(customerData)
        return customerJpaMapper.toDomain(customerSaved)
    }
                       }