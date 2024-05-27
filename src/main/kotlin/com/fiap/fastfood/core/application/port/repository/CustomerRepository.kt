package com.fiap.fastfood.core.application.port.repository

import com.fiap.fastfood.core.entity.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<CustomerEntity, Long> {
    fun findByCpf(cpf: String): CustomerEntity?
    fun existsByCpf(cpf: String): Boolean
}