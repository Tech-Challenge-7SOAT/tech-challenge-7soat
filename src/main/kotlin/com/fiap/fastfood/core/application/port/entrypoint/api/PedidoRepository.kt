package com.fiap.fastfood.core.application.port.entrypoint.api

import com.fiap.fastfood.core.domain.Pedido

interface PedidoRepository {
    fun getPedidoById(id: String): Pedido;
}