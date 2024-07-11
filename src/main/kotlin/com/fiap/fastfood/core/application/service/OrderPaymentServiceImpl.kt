package com.fiap.fastfood.core.application.service

import com.fiap.fastfood.core.application.port.service.OrderPaymentService
import com.fiap.fastfood.core.application.port.service.OrderService
import com.fiap.fastfood.core.valueObject.Status
import org.springframework.stereotype.Service

@Service
class OrderPaymentServiceImpl(
    private val orderService: OrderService
) : OrderPaymentService {

    override fun save(orderId: Long, status: Status): Boolean {
        val order = orderService
            .findOrderById(orderId)
            .copy(status = status)
            .also { orderService.save(it) }

        TODO("Not yet implemented")
    }
}
