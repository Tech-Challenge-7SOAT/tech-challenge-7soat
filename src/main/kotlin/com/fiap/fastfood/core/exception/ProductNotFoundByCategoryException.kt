package com.fiap.produto.exception

class ProductNotFoundByCategoryException(override val message: String, var httpStatus: Int): Exception(){
}