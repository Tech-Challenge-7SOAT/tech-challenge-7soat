package com.fiap.fastfood.core.application.port.repository

import com.fiap.fastfood.core.domain.Cliente

interface ClienteRepository {
    fun getClientPorCpf(cpf: String): Cliente;
}