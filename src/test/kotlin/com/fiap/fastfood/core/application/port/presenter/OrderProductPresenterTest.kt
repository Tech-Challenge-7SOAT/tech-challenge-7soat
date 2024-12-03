package com.fiap.fastfood.core.application.port.presenter

import com.fiap.fastfood.core.entity.OrderProductEntity
import com.fiap.fastfood.core.entity.ProductEntity
import com.fiap.fastfood.core.valueObject.ProductCategory
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class OrderProductPresenterTest {

    @Autowired
    private lateinit var presenter: OrderProductPresenter

    @Test
    fun toDTO() {
        // Given
        val orderProductEntity = OrderProductEntity(
            quantity = 1,
            order = null,
            product = ProductEntity(
                id = 1,
                isActive = true,
                name = "Hamburguer",
                description = "Hamburguer de carne",
                price = 10.0,
                category = ProductCategory.LANCHE
            )
        )

        // When
        val orderProductDTO = presenter.toDTO(orderProductEntity)

        // Then
        assertEquals(orderProductEntity.quantity, orderProductDTO.quantity)
    }
}
