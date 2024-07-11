package com.fiap.fastfood.core.application.port.service

import com.fiap.fastfood.core.valueObject.Status

interface OrderPaymentService {
    fun save(orderId: Long, status: Status): Boolean
}
