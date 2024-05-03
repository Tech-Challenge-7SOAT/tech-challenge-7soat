package com.fiap.fastfood.adapter.driver

import com.fiap.fastfood.core.application.service.ClienteService
import com.fiap.fastfood.core.domain.Cliente
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController("clientes")
class ClienteController(private val clienteService: ClienteService) {
    @GetMapping("/cliente")
    fun clientePorCpf(@RequestParam id: String): Cliente {
        return clienteService.retornarClientePorCpf(id)
    }
}