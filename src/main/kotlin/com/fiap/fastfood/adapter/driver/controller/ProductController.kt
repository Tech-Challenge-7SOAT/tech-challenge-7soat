package com.fiap.fastfood.adapter.driver.controller

import com.fiap.fastfood.core.application.port.presenter.ProductPresenter
import com.fiap.fastfood.core.application.useCase.product.ProductUseCase
import com.fiap.fastfood.core.dto.product.ProductDTO
import com.fiap.fastfood.core.valueObject.ProductCategory
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Product", description = "Create, remove, edit and search products by category.")
@RestController
@RequestMapping("/products")
class ProductController(
    private val productUseCase: ProductUseCase,
    private val presenter: ProductPresenter
) {

    @PostMapping("/product")
    @Operation(summary = "Create a product")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Product created successfully"),
        ApiResponse(responseCode = "400", description = "When the category type is invalid"),
        ApiResponse(responseCode = "404", description = "When can't find a product using the ID provided"),
        ApiResponse(responseCode = "500", description = "When it is not possible to create the product")
    ])
    fun createProduct(
        @Valid @RequestBody productBody: ProductDTO
    ): ResponseEntity<Any> {
        productUseCase.create(productBody)

        return ResponseEntity("Product created successfully", HttpStatus.CREATED)
    }

    @GetMapping("/category")
    @Operation(summary = "Find products by category")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Returns a list of products based on the specified category"),
        ApiResponse(responseCode = "400", description = "When the category type is invalid"),
        ApiResponse(responseCode = "404", description = "When can't find a product using the ID provided"),
        ApiResponse(responseCode = "500", description = "When it is not possible to find the product")
    ])
    fun findProductsByCategory(
        @RequestParam category: ProductCategory
    ): List<ProductDTO> {
        val products = productUseCase.findByCategory(category)

        return products.map { product -> presenter.toDTO(product) }
    }

    @GetMapping
    @Operation(summary = "Find all products")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Returns a list of products"),
        ApiResponse(responseCode = "404", description = "When can't find a products")
    ])
    fun findAllProducts(
        @RequestParam(required = false) isActive: Boolean
    ): List<ProductDTO> {
        val products = productUseCase.findAllProducts(isActive)

        return products.map { product -> presenter.toDTO(product) }
    }

    @PatchMapping("/product/{id}")
    @Operation(summary = "Edit a product")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Product edited successfully"),
        ApiResponse(responseCode = "404", description = "When can't find a product using the ID provided"),
        ApiResponse(responseCode = "500", description = "When it is not possible to update the product")
    ])
    fun updateProduct(
        @PathVariable(required = true) id: Long,
        @Valid @RequestBody productBody: ProductDTO
    ): ResponseEntity<Any> {
        productUseCase.update(id, productBody)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/product/{id}")
    @Operation(summary = "Delete a product")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Product deleted successfully"),
        ApiResponse(responseCode = "404", description = "When can't find a product using the ID provided"),
        ApiResponse(responseCode = "500", description = "When it is not possible to delete the product")
    ])
    fun removeProduct(
        @PathVariable(required = true) id: Long
    ): ResponseEntity<Any> {
        productUseCase.delete(id)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
