package com.fiap.produto.exception

class InvalidProductCategoryException(override val message: String, var httpStatus: Int): Exception(){
}