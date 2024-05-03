package com.fiap.fastfood.adapter.driven.infra

import com.fiap.fastfood.core.application.port.ClienteRepository
import com.fiap.fastfood.core.domain.Cliente
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
class ClienteRepositoryImpl : ClienteRepository {
    override fun getClientPorCpf(cpf: String): Cliente {
        return Cliente(
            id = UUID.randomUUID().toString(),
            firstName = "Gabriel",
            lastName = "Caldeira",
            cpf = "41561540846",
            email = "gabriel_mariusso@hotmail.com",
            createdAt = LocalDate.now(),
            updatedAt = LocalDate.now(),
            createdBy = "Admin",
            updatedBy = "Admin"
        )
    }
}