package com.fiap.fastfood.core.domain

data class CustomerId(val intValue: Int)

data class CustomerFullName(internal val firstName: String, internal val lastName: String)

data class CustomerCpf(val cpf: String)

data class CustomerEmail(internal val email: String)

data class CustomerPhoneNumber(internal val phoneNumber: String)