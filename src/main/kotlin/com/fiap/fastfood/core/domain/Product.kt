//package com.fiap.fastfood.core.domain
//
//import com.fiap.fastfood.core.entity.ProductEntity
//import com.fiap.fastfood.core.valueObject.Category
//import java.time.LocalDateTime
//
//class Product(
//    private val id: String,
//    private val isActive: Boolean,
//    private val name: String,
//    private val description: String,
//    private val price: Double,
//    private val timeToPrepare: Int,
//    private val category: Category,
//    private val orders: List<Order>,
//    private val createdAt: LocalDateTime,
//    private val updatedAt: LocalDateTime
//) {
//    fun toEntity(): ProductEntity {
//        return ProductEntity(id, isActive, name, description, price, timeToPrepare, category, orders.map { it.toEntity() }, createdAt, updatedAt);
//    }
//}