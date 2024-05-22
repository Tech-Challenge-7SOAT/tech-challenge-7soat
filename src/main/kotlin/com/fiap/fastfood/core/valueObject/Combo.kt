package com.fiap.fastfood.core.valueObject

class Combo(
    val id: String,
    val pedidoId: String,
    val produtoId: String) {
    fun hasProducts(): Boolean {
        return pedidoId.isNotEmpty()
    }
}