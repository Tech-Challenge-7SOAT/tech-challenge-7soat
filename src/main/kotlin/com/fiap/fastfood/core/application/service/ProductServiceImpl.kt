package com.fiap.fastfood.core.application.service

import com.fiap.fastfood.core.application.port.repository.ProductRepository
import com.fiap.fastfood.core.application.port.service.ProductService
import com.fiap.fastfood.core.domain.Product
import com.fiap.fastfood.core.entity.ProductEntity
import com.fiap.fastfood.core.valueObject.ProductCategory
import com.fiap.produto.exception.ProductNotFoundByCategoryException
import com.fiap.produto.exception.ProductNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService {

    override fun create(product: Product) {
        val productEntity = product.toEntity()
        productRepository.save(productEntity).toDomain()
    }

    override fun findByCategory(category: ProductCategory): List<Product> {
        return productRepository.findByCategory(category)
            .filter { it.isActive }
            .map { it.toDomain() }
            .takeIf { it.isNotEmpty() }
            ?: throw ProductNotFoundByCategoryException(
                "No products found for category <$category>",
                HttpStatus.NOT_FOUND.value()
            )
    }

    override fun update(id: Long, product: Product) {
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
