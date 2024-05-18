package com.fiap.fastfood.core.application.usecase

import com.fiap.fastfood.core.domain.Customer

interface FindAllCustomersUseCase {

    fun fetchAllPersisted(): Collection<Customer>
}