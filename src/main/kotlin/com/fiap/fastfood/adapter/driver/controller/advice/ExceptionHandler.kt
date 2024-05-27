//package com.fiap.fastfood.adapter.driver.controller.advice
//
//import com.fiap.fastfood.core.domain.ExceptionResponse
//import com.fiap.fastfood.core.exception.InvalidIdTypeException
//import com.fiap.fastfood.core.exception.OrderNotFoundException
//import com.fiap.produto.exception.InternalServerErrorException
//import com.fiap.produto.exception.InvalidProductCategoryException
//import com.fiap.produto.exception.ProductNotFoundByCategoryException
//import com.fiap.produto.exception.ProductNotFoundException
//import org.springframework.dao.DataIntegrityViolationException
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.ControllerAdvice
//import org.springframework.web.bind.annotation.ExceptionHandler
//import org.springframework.web.bind.annotation.ResponseStatus
//import org.springframework.web.server.ResponseStatusException
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
//
//@ControllerAdvice
//class ExceptionHandler: ResponseEntityExceptionHandler() {
//
//    @ExceptionHandler(InvalidProductCategoryException::class)
//    fun handleInvalidProductCategoryException(e: InvalidProductCategoryException): ResponseEntity<ExceptionResponse> {
//        return ResponseEntity.status(e.httpStatus).body(ExceptionResponse(e.httpStatus, e.message))
//    }
//
//    @ExceptionHandler(ProductNotFoundByCategoryException::class)
//    fun handleProductNotFoundByCategoryException(e: ProductNotFoundByCategoryException): ResponseEntity<ExceptionResponse> {
//        return ResponseEntity.status(e.httpStatus).body(ExceptionResponse(e.httpStatus, e.message))
//    }
//
//    @ExceptionHandler(ProductNotFoundException::class)
//    fun handleProductNotFoundException(e: ProductNotFoundException): ResponseEntity<ExceptionResponse> {
//        return ResponseEntity.status(e.httpStatus).body(ExceptionResponse(e.httpStatus, e.message))
//    }
//
//    @ExceptionHandler(InternalServerErrorException::class)
//    fun handleInternalServerErrorException(e: InternalServerErrorException): ResponseEntity<ExceptionResponse> {
//        return ResponseEntity.status(e.httpStatus).body(ExceptionResponse(e.httpStatus, e.message))
//    }
//
//    @ExceptionHandler(DataIntegrityViolationException::class)
//    fun handleDataIntegrityViolationException(ex: DataIntegrityViolationException): ResponseEntity<ExceptionResponse> {
//        val errorMessage = "Integrity Violation Exception"
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionResponse(HttpStatus.CONFLICT.value(), ex.message ?: errorMessage))
//    }
//
//    @ExceptionHandler(InvalidIdTypeException::class)
//    fun handleInvalidIdTypeException(e: InvalidIdTypeException): ResponseEntity<ExceptionResponse> {
//        val errorMessage = "Ids must be numeric type"
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse(HttpStatus.BAD_REQUEST.value(), e.message ?: errorMessage))
//    }
//
//    @ExceptionHandler(OrderNotFoundException::class)
//    fun handleOrderNotFoundException(e: OrderNotFoundException): ResponseEntity<ExceptionResponse> {
//        val errorMessage = "Order not found"
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionResponse(HttpStatus.NOT_FOUND.value(), errorMessage))
//    }
//
//    @ExceptionHandler(IllegalArgumentException::class)
//    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ExceptionResponse> {
//        val errorMessage = "Order id cannot be null"
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse(HttpStatus.BAD_REQUEST.value(), errorMessage))
//    }
//
////    @ExceptionHandler(ResponseStatusException::class)
////    @ResponseStatus(HttpStatus.BAD_REQUEST)
////    fun handleResponseStatusException(ex: ResponseStatusException): ResponseEntity<ExceptionResponse> {
////        ex.printStackTrace()
////
////        val errorResponse = ExceptionResponse(
////            httpCode = ex.statusCode.value(),
////            message = ex.reason ?: "Unexpected error"
////        )
////        return ResponseEntity(errorResponse, ex.statusCode)
////    }
//}