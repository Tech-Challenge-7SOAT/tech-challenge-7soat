package com.fiap.fastfood.adapter.driven.infra

import com.fiap.fastfood.core.application.port.ClienteRepository
import com.fiap.fastfood.core.domain.Cliente
import org.springframework.stereotype.Repository

@Repository
class ClienteRepositoryImpl : ClienteRepository {
    override fun getClientPorCpf(id: String): Cliente {
        TODO("Not yet implemented")
    }
}