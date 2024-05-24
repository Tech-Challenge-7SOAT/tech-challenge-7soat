package com.fiap.fastfood.adapter.driver

import com.fiap.fastfood.core.application.port.service.ProductService
import com.fiap.fastfood.core.domain.Product
import com.fiap.fastfood.core.domain.enumeration.ProductCategory
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
class ProductController(
    val productService: ProductService
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
        @Valid @RequestBody productBody: Product
    ): ResponseEntity<Any> {
        productService.create(productBody)

        return ResponseEntity("Product created successfully", HttpStatus.CREATED)
    }

    @GetMapping("/product")
    @Operation(summary = "Find products by category")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Returns a list of products based on the specified category"),
        ApiResponse(responseCode = "400", description = "When the category type is invalid"),
        ApiResponse(responseCode = "404", description = "When can't find a product using the ID provided"),
        ApiResponse(responseCode = "500", description = "When it is not possible to find the product")
    ])
    fun findProductsByCategory(
        @RequestParam category: String
    ): List<Product> {

        return productService.findByCategory(category)
    }

    @PatchMapping("/product/{productId}")
    @Operation(summary = "Edit a product")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Product edited successfully"),
        ApiResponse(responseCode = "404", description = "When can't find a product using the ID provided"),
        ApiResponse(responseCode = "500", description = "When it is not possible to update the product")
    ])
    fun updateProduct(
        @PathVariable productId: Long,
        @Valid @RequestBody productBody: Product
    ): ResponseEntity<Any> {
        productService.update(productId, productBody)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/product/{productId}")
    @Operation(summary = "Delete a product")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Product deleted successfully"),
        ApiResponse(responseCode = "404", description = "When can't find a product using the ID provided"),
        ApiResponse(responseCode = "500", description = "When it is not possible to delete the product")
    ])
    fun removeProduct(
        @PathVariable productId: Long
    ): ResponseEntity<Any> {
        productService.delete(productId)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}