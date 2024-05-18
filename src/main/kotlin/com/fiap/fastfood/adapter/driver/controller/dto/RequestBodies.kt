package com.fiap.fastfood.adapter.driver.controller.dto;

data class SaveCustomerBodyDto(
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val email: String,
    val phoneNumber: String
)