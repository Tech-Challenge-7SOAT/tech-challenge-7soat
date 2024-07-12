package com.fiap.fastfood.core.application.useCase.product

import com.fiap.fastfood.core.application.port.gateway.ProductRepository
import com.fiap.fastfood.core.dto.ProductDTO
import com.fiap.fastfood.core.entity.ProductEntity
import com.fiap.fastfood.core.valueObject.ProductCategory
import com.fiap.produto.exception.ProductNotFoundByCategoryException
import com.fiap.produto.exception.ProductNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class ProductUseCaseImpl(
    private val productRepository: ProductRepository
) : ProductUseCase {

    override fun create(product: ProductDTO) {
        val productEntity = product.toEntity()
        productRepository.save(productEntity)
    }

    override fun findByCategory(category: ProductCategory): List<ProductEntity> {
        return productRepository.findByCategory(category)
            .filter { it.isActive }
            .takeIf { it.isNotEmpty() }
            ?: throw ProductNotFoundByCategoryException(
                "No products found for category <$category>",
                HttpStatus.NOT_FOUND.value()
            )
    }

    override fun update(id: Long, product: ProductDTO) {
        val existingProduct = productRepository.findById(id)
            .orElseThrow { ProductNotFoundException("Product <$id> not found", HttpStatus.NOT_FOUND.value()) }

        existingProduct.apply {
            name = product.name
            category = product.category
            description = product.description
            price = product.price
            timeToPrepare = product.timeToPrepare
        }

        productRepository.save(existingProduct)
    }

    override fun delete(id: Long) {
        val product = findProductById(id)
            .copy(
                deletedAt = Timestamp(System.currentTimeMillis()),
                isActive = false
            )
        productRepository.save(product)
    }

    fun findProductById(id: Long): ProductEntity {
        return productRepository.findById(id)
            .orElseThrow { throw ProductNotFoundException("Product <$id> not found", HttpStatus.NOT_FOUND.value()) }
    }
}
