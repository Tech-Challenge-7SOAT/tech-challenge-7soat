package com.fiap.fastfood.core.domain

import com.fiap.fastfood.core.entity.ProductEntity
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.sql.Timestamp

class Product(
    val id: Long? = null,
    var isActive: Boolean = true,
    @field:NotBlank
    var name: String,
    @field:NotBlank
    var description: String,
    @field:NotNull
    @field:Min(0)
    var price: Double,
    @field:NotNull
    @field:Min(0)
    var timeToPrepare: Int,
    @field:NotBlank
    var category: String,
    val createdAt: Timestamp? = null,
    val updatedAt: Timestamp? = null
) {

    fun toEntity(): ProductEntity {
        return ProductEntity(
            isActive = isActive,
            name = name,
            description = description,
            price = price,
            timeToPrepare = timeToPrepare,
            category = category
        )
    }
}
