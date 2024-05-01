package com.fiap.fastfood.core.application.service

import com.fiap.fastfood.core.application.port.PedidoRepository
import com.fiap.fastfood.core.domain.Pedido
import org.springframework.stereotype.Service

@Service
class PedidoService(private val pedidoRepository: PedidoRepository) {
    fun retornarPedidoPorId(id: String): Pedido {
        return pedidoRepository.getPedidoById(id)
    }
}