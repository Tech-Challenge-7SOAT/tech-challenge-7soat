package com.fiap.fastfood.core.application.port.service

import com.fiap.fastfood.core.domain.Order

interface OrderService {
   fun retornarPedidoPorId(id: String): Order
   fun salvar(order: Order): Order
   fun excluirPedidoPorId(id: String)
   fun listarPedidos(): List<Order>
}