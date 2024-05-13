package com.fiap.fastfood.core.application.port.service

import com.fiap.fastfood.core.domain.Pedido

interface PedidoService {
   fun retornarPedidoPorId(id: String): Pedido
   fun salvar(pedido: Pedido): Pedido
   fun excluirPedidoPorId(id: String)
   fun listarPedidos(): List<Pedido>
}