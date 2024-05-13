package com.fiap.fastfood.adapter.driver

import com.fiap.fastfood.core.application.port.service.PedidoService
import com.fiap.fastfood.core.domain.Pedido
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController("pedidos")
class PedidoController(private val pedidoService: PedidoService) {
    @GetMapping("/pedido/{id}")
    fun pedidoPorId(@RequestParam id: String): Pedido {
        return pedidoService.retornarPedidoPorId(id)
    }

    @PostMapping("/pedido")
    fun salvarPedido(@RequestBody pedido: Pedido): Pedido {
        return pedidoService.salvar(pedido)
    }

    @DeleteMapping("/pedido/{id}")
    fun excluirPedido(@RequestParam id: String) {
        pedidoService.excluirPedidoPorId(id)
    }

    @GetMapping("/")
    fun listarPedidos(): List<Pedido> {
        return pedidoService.listarPedidos()
    }
}