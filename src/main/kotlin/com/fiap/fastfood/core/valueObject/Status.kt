package com.fiap.fastfood.core.valueObject

enum class Status(val order: Int) {
    PRONTO(1),
    EM_PREPARACAO(2),
    RECEBIDO(3),
    FINALIZADO(4),
    PENDENTE(5)
}