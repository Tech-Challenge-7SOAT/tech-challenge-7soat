package com.fiap.fastfood.core.application.port.repository

import com.fiap.fastfood.core.entity.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<CustomerEntity, String> {
    fun findByCpf(cpf: String): CustomerEntity?
}