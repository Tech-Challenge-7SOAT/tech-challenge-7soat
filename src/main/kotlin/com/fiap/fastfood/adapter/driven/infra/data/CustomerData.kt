package com.fiap.fastfood.adapter.driven.infra.data

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

@Table(name = "customers")
data class CustomerData(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id:Int? = null,
                        val firstName:String? = null,
                        val lastName:String? = null,
                        val cpf:String? = null,
                        val email:String? = null,
                        val phoneNumber:String? = null,
                        val createdAt: @NotNull LocalDateTime? = null) {

    data class Builder(private var id: Int? = null,
                       private var firstName: String? = null,
                       private var lastName: String? = null,
                       private var cpf: String? = null,
                       private var email: String? = null,
                       private var phoneNumber: String? = null,
                       private var createdAt: LocalDateTime? = null) {

        fun id(id: Int?) = apply { this.id = id }
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun cpf(cpf: String) = apply { this.cpf = cpf }
        fun email(email: String) = apply { this.email = email }
        fun phoneNumber(phone: String) = apply { this.phoneNumber = phone }
        fun createdAt(createdAt: LocalDateTime) = apply { this.createdAt = createdAt }
        fun build() = CustomerData(id, firstName, lastName, cpf, email, phoneNumber, createdAt)
    }
}
