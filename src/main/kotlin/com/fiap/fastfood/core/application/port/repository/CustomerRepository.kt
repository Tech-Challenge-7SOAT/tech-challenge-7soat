package com.fiap.fastfood.core.application.port.repository

import com.fiap.fastfood.core.entity.CustomerEntity
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerEntity, String>