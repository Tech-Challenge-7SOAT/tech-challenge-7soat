package com.fiap.produto.exception

class InternalServerErrorException(override val message: String, var httpStatus: Int): Exception(){
}