package com.fiap.fastfood.core.application.service

import com.fiap.fastfood.core.application.port.repository.ProductRepository
import com.fiap.fastfood.core.application.port.service.ProductService
import com.fiap.fastfood.core.domain.Product
import com.fiap.fastfood.core.domain.enumeration.ProductCategory
import com.fiap.produto.exception.InvalidProductCategoryException
import com.fiap.produto.exception.ProductNotFoundByCategoryException
import com.fiap.produto.exception.ProductNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService {

    override fun create(product: Product) {
        validateProductCategory(product.category)
        val productEntity = product.toEntity()
        productRepository.save(productEntity).toDomain()
    }

    override fun findByCategory(category: String): List<Product> {
        validateProductCategory(category)
        val products = productRepository.findByCategory(category.uppercase())
        val productsMap = products.map { it.toDomain() }
        return productsMap.takeIf { it.isNotEmpty() }
            ?: throw ProductNotFoundByCategoryException("No products found for category <$category>", HttpStatus.NOT_FOUND.value())
    }

    override fun update(id: Long, product: Product) {
        validateProductCategory(product.category)
        findProductById(id)
        productRepository.save(product.toEntity()).toDomain()
    }

    override fun delete(id: Long) {
        findProductById(id)
        productRepository.deleteById(id)
    }

    fun validateProductCategory(category: String) {
        ProductCategory.values().find { it.name == category.uppercase() }
            ?: throw InvalidProductCategoryException("$category is not a valid category", HttpStatus.BAD_REQUEST.value())
    }

    fun findProductById(id: Long) {
        productRepository.findById(id)
            .orElseThrow { throw ProductNotFoundException("Product <$id> not found", HttpStatus.NOT_FOUND.value()) }
    }
}