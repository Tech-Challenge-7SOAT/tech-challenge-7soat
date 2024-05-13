package com.fiap.fastfood.core.application.service

import com.fiap.fastfood.core.application.port.repository.ClienteRepository
import com.fiap.fastfood.core.domain.Cliente
import org.springframework.stereotype.Service

@Service
class ClienteService(private val clienteRepository: ClienteRepository) {
    fun retornarClientePorCpf(cpf: String): Cliente {
        return clienteRepository.getClientPorCpf(cpf)
    }
}