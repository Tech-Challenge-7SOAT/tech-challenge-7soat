package com.fiap.fastfood.core.application.port.repository

import com.fiap.fastfood.core.entity.PedidoEntity
import org.springframework.data.repository.CrudRepository

interface PedidoRepository : CrudRepository<PedidoEntity, String> {
}