package com.fiap.fastfood.adapter.driver.controller.advice

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

    @ExceptionHandler(InternalServerErrorException::class)
    fun handleInternalServerErrorException(e: InternalServerErrorException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(e.httpStatus).body(ExceptionResponse(e.httpStatus, e.message))
    }

    @ExceptionHandler(MissingPathVariableException::class)
    fun handleMissingPathVariableException(e: MissingPathVariableException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse(HttpStatus.BAD_REQUEST.value(), "Product id is required"))
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMissingRequestParamException(e: MissingServletRequestParameterException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse(HttpStatus.BAD_REQUEST.value(), "Category parameter is required"))
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(ex: DataIntegrityViolationException): ResponseEntity<ExceptionResponse> {
        val errorMessage = "Integrity Violation Exception"
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionResponse(HttpStatus.CONFLICT.value(), ex.message ?: errorMessage))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ExceptionResponse> {
        val errorMessage = "Method Argument Not Valid Exception"
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.message ?: errorMessage))
    }
}