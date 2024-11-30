package com.fiap.fastfood.core.application.port.gateway

import com.fiap.fastfood.core.dto.payment.PaymentDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import java.io.IOException
import java.io.Serializable

@Repository
class PaymentGateway(private val restTemplate: RestTemplate) {

    @Value("\${api.payment-gateway:localhost:8080}")
    private lateinit var paymentGatewayUrl: String

    fun pay(content: PaymentDTO): String {
        val response = restTemplate.postForEntity(paymentGatewayUrl, content, String::class.java)
        return response.body ?: throw IOException("Error on payment")
    }
}
