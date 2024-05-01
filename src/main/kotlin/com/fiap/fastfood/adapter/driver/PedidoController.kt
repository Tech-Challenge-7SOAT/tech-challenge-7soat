package com.fiap.fastfood.adapter.driver

import com.fiap.fastfood.core.application.service.PedidoService
import com.fiap.fastfood.core.domain.Pedido
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController("pedidos")
class PedidoController(private val pedidoService: PedidoService) {
    @GetMapping("/pedido/{id}")
    fun pedidoPorId(@RequestParam id: String): Pedido {
        return pedidoService.retornarPedidoPorId(id)
    }
}