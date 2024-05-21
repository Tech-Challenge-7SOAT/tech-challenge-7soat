package com.fiap.fastfood.adapter.driver.advice

import com.fiap.fastfood.core.domain.ExceptionResponse
import com.fiap.produto.exception.InternalServerErrorException
import com.fiap.produto.exception.InvalidProductCategoryException
import com.fiap.produto.exception.ProductNotFoundByCategoryException
import com.fiap.produto.exception.ProductNotFoundException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingPathVariableException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class ExceptionController {

    @ExceptionHandler(InvalidProductCategoryException::class)
    fun handleInvalidProductCategoryException(e: InvalidProductCategoryException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(e.httpStatus).body(ExceptionResponse(e.httpStatus, e.message))
    }

    @ExceptionHandler(ProductNotFoundByCategoryException::class)
    fun handleProductNotFoundByCategoryException(e: ProductNotFoundByCategoryException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(e.httpStatus).body(ExceptionResponse(e.httpStatus, e.message))
    }

    @ExceptionHandler(ProductNotFoundException::class)
    fun handleProductNotFoundException(e: ProductNotFoundException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(e.httpStatus).body(ExceptionResponse(e.httpStatus, e.message))
    }

    @ExceptionHandler(InternalServerErrorException::class) //TODO
    fun handleInternalServerErrorException(e: InternalServerErrorException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(e.httpStatus).body(ExceptionResponse(e.httpStatus, e.message))
    }

    @ExceptionHandler(MissingPathVariableException::class) //When a path variable is not provided
    fun handleMissingPathVariableException(e: MissingPathVariableException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse(HttpStatus.BAD_REQUEST.value(), "Product id is required"))
    }

    @ExceptionHandler(MissingServletRequestParameterException::class) //When a request param is not provided
    fun handleMissingRequestParamException(e: MissingServletRequestParameterException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse(HttpStatus.BAD_REQUEST.value(), "Category parameter is required"))
    }

    @ExceptionHandler(DataIntegrityViolationException::class) //TODO
    fun handleDataIntegrityViolationException(ex: DataIntegrityViolationException): ResponseEntity<ExceptionResponse> {
        val errorMessage = "Integrity Violation Exception"
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionResponse(HttpStatus.CONFLICT.value(), ex.message ?: errorMessage))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class) //TODO
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ExceptionResponse> {
        val errorMessage = "Method Argument Not Valid Exception"
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.message ?: errorMessage))
    }
}