package com.fiap.fastfood.adapter.driver.advice

import com.fiap.fastfood.core.exception.InvalidIdTypeException
import com.fiap.fastfood.core.exception.OrderNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler()
    fun handleInvalidIdTypeException(e: InvalidIdTypeException): ResponseEntity<Any> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler()
    fun handleOrderNotFoundException(e: OrderNotFoundException): ResponseEntity<Any> {
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }
}