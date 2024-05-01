package com.fiap.fastfood.core.application.port

import com.fiap.fastfood.core.domain.Pedido

interface PedidoRepository {
    fun getPedidoById(id: String): Pedido;
}