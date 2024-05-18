package com.fiap.fastfood.core.domain

data class Customer
    private constructor(
        internal val id: CustomerId? = null,
        internal val fullName: CustomerFullName,
        internal val cpf: CustomerCpf,
        internal val email: CustomerEmail,
        internal val phoneNumber: CustomerPhoneNumber) {

    data class Builder(
            var id: CustomerId? = null,
            var fullName: CustomerFullName? = null,
            var cpf: CustomerCpf? = null,
            var email: CustomerEmail? = null,
            var phoneNumber: CustomerPhoneNumber? = null) {

        fun id(id: CustomerId?) = apply { this.id = id }
        fun fullName(fullName: CustomerFullName) = apply { this.fullName = fullName }
        fun cpf(cpf: CustomerCpf?) = apply { this.cpf = cpf }
        fun email(email: CustomerEmail) = apply { this.email = email }
        fun phoneNumber(phoneNumber: CustomerPhoneNumber) = apply { this.phoneNumber = phoneNumber }
        fun build() = Customer(id, fullName!!, cpf!!, email!!, phoneNumber!!)
    }
}

object CustomerFunctions {

    val customerId          = composeOptional(CustomerId::intValue, Customer::id)
    val customerFirstName   = compose(CustomerFullName::firstName, Customer::fullName)
    val customerLastName    = compose(CustomerFullName::lastName, Customer::fullName)
    val customerCpf         = compose(CustomerCpf::cpf, Customer::cpf)
    val customerEmail       = compose(CustomerEmail::email, Customer::email)
    val customerPhoneNumber = compose(CustomerPhoneNumber::toString, Customer::phoneNumber)

    private fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C = { f(g(it)) }
    private fun <A, B, C> composeOptional(f: (B) -> C, g: (A) -> B?): (A) -> C? = { x -> g(x)?.let { f(it) } }
}