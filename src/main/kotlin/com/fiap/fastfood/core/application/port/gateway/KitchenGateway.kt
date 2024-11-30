package com.fiap.fastfood.core.application.port.gateway

import com.fiap.fastfood.core.dto.checkout.KitchenPreparationDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import java.io.Serializable

@Repository
class KitchenGateway(@Autowired private val restTemplate: RestTemplate) {

    @Value("\${api.kichen-gateway:localhost:8080}")
    private lateinit var kitchenGatewayUrl: String

    fun prepare(content: KitchenPreparationDTO): String {
        val response = restTemplate.postForEntity(kitchenGatewayUrl, content, String::class.java)
        return response.body ?: throw Exception("Error on preparing order")
    }
}
