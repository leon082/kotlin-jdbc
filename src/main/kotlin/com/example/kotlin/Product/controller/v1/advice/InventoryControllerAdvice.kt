package com.example.kotlin.Product.controller.v1.advice

import com.example.kotlin.Product.builder.ErrorResponseDTOBuilder
import com.example.kotlin.Product.controller.v1.InventoryController
import com.example.kotlin.Product.dto.ErrorResponseDTO
import com.example.kotlin.Product.exception.GenericError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice(assignableTypes = [InventoryController::class])
class InventoryControllerAdvice: ResponseEntityExceptionHandler() {

    @ExceptionHandler(GenericError::class)
    fun controllGenericError(e : GenericError):ResponseEntity<ErrorResponseDTO>{
        print("GenericError")
        val result = ErrorResponseDTOBuilder().withCode(e.code).withTitle(e.error).withHttpStatus(HttpStatus.BAD_REQUEST).build()
        return ResponseEntity.status(404).body(result)
    }

    /*@ExceptionHandler(Exception::class)
    fun controllException(e : java.lang.Exception):ResponseEntity<ErrorResponseDTO>{
        println("Exception error")
        val result = ErrorResponseDTOBuilder().withCode("500").withTitle(e.message).withHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR).build()
        return ResponseEntity.status(500).body(result)
    }*/


}