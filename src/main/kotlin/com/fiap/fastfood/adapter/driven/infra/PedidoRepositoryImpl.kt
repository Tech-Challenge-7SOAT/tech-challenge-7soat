package com.fiap.fastfood.adapter.driven.infra

import com.fiap.fastfood.core.application.port.PedidoRepository
import com.fiap.fastfood.core.domain.Pedido
import org.springframework.stereotype.Repository

@Repository
class PedidoRepositoryImpl : PedidoRepository {
    override fun getPedidoById(id: String): Pedido {
        TODO("Not yet implemented")
    }
}