package com.fiap.fastfood.core.application.useCase.product

import com.fiap.fastfood.core.application.port.gateway.ProductRepository
import com.fiap.fastfood.core.dto.product.ProductDTO
import com.fiap.fastfood.core.entity.ProductEntity
import com.fiap.fastfood.core.valueObject.ProductCategory
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.TestPropertySources
import strikt.api.expectThat
import strikt.assertions.isNotEmpty
import java.util.*

@Profile("test")
@SpringBootTest
@AutoConfigureDataJpa
class ProductUseCaseImplTest {

    private lateinit var productRepository: ProductRepository
    private lateinit var productUseCase: ProductUseCaseImpl

    @BeforeEach
    fun setup() {
        productRepository = mockk()
        productUseCase = ProductUseCaseImpl(productRepository)
    }

    private val entity = ProductEntity(
        id = 1,
        name = "Test",
        category = ProductCategory.LANCHE,
        description = "Test",
        price = 1.0,
        timeToPrepare = 1
    )

    private val product = ProductDTO(
        name = "Test",
        category = ProductCategory.LANCHE,
        description = "Test",
        price = 1.0,
        timeToPrepare = 1
    )

    @Test
    fun create() {
        every { productRepository.save(any()) } returns entity

        assertDoesNotThrow {
            productUseCase.create(product)
        }
    }

    @Test
    fun findByCategory() {
        every { productRepository.findByCategory(any()) } returns listOf(entity)

        val result = productUseCase.findByCategory(ProductCategory.LANCHE)

        expectThat(result).isNotEmpty()
    }

    @Test
    fun findAllProducts() {
        every { productRepository.findAll() } returns listOf(entity)

        val result = productUseCase.findAllProducts(isActive = true)

        expectThat(result).isNotEmpty()
    }

    @Test
    fun update() {
        every { productRepository.findById(any()) } returns Optional.of(entity)
        every { productRepository.save(any()) } returns entity

        assertDoesNotThrow {
            productUseCase.update(1, product)
        }
    }

    @Test
    fun delete() {
        every { productRepository.findById(any()) } returns Optional.of(entity)
        every { productRepository.save(any()) } returns entity

        assertDoesNotThrow {
            productUseCase.delete(1)
        }
    }

    @Test
    fun findProductById() {
        every { productRepository.findById(any()) } returns Optional.of(entity)

        assertDoesNotThrow {
            productUseCase.findProductById(1)
        }
    }
}
