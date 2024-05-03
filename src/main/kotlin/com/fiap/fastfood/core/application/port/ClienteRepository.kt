package com.fiap.fastfood.core.application.port

import com.fiap.fastfood.core.domain.Cliente

interface ClienteRepository {
    fun getClientPorCpf(id: String): Cliente;
}