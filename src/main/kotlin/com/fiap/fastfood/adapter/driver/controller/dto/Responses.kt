package com.fiap.fastfood.adapter.driver.controller.dto;

data class CustomerDto
    private constructor(val firstName: String?,
                        val lastName: String?,
                        val cpf: String?,
                        val email: String?,
                        val phoneNumber: String?
    ) {
    data class Builder(
            var firstName: String? = null,
            var lastName: String? = null,
            var cpf: String? = null,
            var email: String? = null,
            var phoneNumber: String? = null) {

        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun cpf(cpf: String) = apply { this.cpf = cpf }
        fun email(email: String) = apply { this.email = email }
        fun phoneNumber(phoneNumber: String) = apply { this.phoneNumber = phoneNumber }
        fun build() = CustomerDto(firstName, lastName, cpf, email, phoneNumber)
        }
    }