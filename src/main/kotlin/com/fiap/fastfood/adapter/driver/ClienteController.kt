package com.fiap.fastfood.adapter.driver

import com.fiap.fastfood.core.application.service.ClienteService
import com.fiap.fastfood.core.domain.Cliente
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController()
class ClienteController(private val clienteService: ClienteService) {
    @GetMapping("/clientes/{cpf}")
    fun clientePorCpf(@PathVariable cpf: String): ResponseEntity<Cliente> {
        return ResponseEntity.ok(clienteService.retornarClientePorCpf(cpf))
    }
}