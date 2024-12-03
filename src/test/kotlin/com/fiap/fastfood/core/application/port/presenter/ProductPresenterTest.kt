package com.fiap.fastfood.core.application.port.presenter

import com.fiap.fastfood.core.entity.ProductEntity
import com.fiap.fastfood.core.valueObject.ProductCategory
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProductPresenterTest {

    @Autowired
    private lateinit var presenter: ProductPresenter

    @Test
    fun toDTO() {
        val product = ProductEntity(
            id = 1,
            isActive = true,
            name = "Hamburguer",
            description = "Hamburguer de carne",
            price = 10.0,
            category = ProductCategory.LANCHE
        )

        val productDTO = presenter.toDTO(product)

        assertEquals(product.id, productDTO.id)
    }
}
