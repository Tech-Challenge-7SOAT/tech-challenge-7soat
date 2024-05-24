package com.fiap.produto.exception

class ProductNotFoundException(override val message: String, var httpStatus: Int): Exception(){
}