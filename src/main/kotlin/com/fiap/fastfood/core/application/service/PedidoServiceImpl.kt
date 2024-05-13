package com.fiap.fastfood.core.application.service

import com.fiap.fastfood.core.application.port.repository.PedidoRepository
import com.fiap.fastfood.core.application.port.service.PedidoService
import com.fiap.fastfood.core.domain.Pedido
import com.fiap.fastfood.core.entity.PedidoEntity
import com.fiap.fastfood.core.exception.PedidoServiceException
import org.springframework.stereotype.Service
import java.util.Optional
import kotlin.jvm.optionals.getOrElse

@Service
class PedidoServiceImpl(private val pedidoRepository: PedidoRepository) : PedidoService {
    override fun retornarPedidoPorId(id: String): Pedido {
        val pedidoEntity: Optional<PedidoEntity> = pedidoRepository.findById(id)

        return pedidoEntity.getOrElse { throw Exception("Pedido não encontrado") }.toPedido()
    }

    override fun salvar(pedido: Pedido): Pedido {
        try {
            if (pedido.hasCombo()) {
                return pedidoRepository.save(pedido.toEntity()).toPedido()
            }

            throw PedidoServiceException("Pedido sem combo")
        } catch (e: Exception) {
            throw PedidoServiceException("Erro ao salvar pedido")
        }
    }

    override fun excluirPedidoPorId(id: String) {
        try {
            pedidoRepository.deleteById(id)
        } catch (e: Exception) {
            throw PedidoServiceException("Erro ao excluir pedido")
        }
    }

    override fun listarPedidos(): List<Pedido> {
        try {
            return pedidoRepository.findAll().map { it.toPedido() }
        } catch (e: Exception) {
            throw PedidoServiceException("Erro ao listar pedidos")
        }
    }
}