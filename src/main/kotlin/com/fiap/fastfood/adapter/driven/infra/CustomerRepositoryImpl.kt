package com.fiap.fastfood.adapter.driven.infra

import com.fiap.fastfood.adapter.driven.infra.data.CustomerData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepositoryImpl : JpaRepository<CustomerData, String> {

    fun findByFirstNameAndLastName(firstName: String, lastName: String): Collection<CustomerData>

    fun existsByCpf(customerCpf: String): Collection<CustomerData>

    fun findByCpf(customerCpf: String)
}