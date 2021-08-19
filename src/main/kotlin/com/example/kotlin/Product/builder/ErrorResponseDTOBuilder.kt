package com.example.kotlin.Product.builder

import com.example.kotlin.Product.dto.ErrorResponseDTO
import org.springframework.http.HttpStatus

class ErrorResponseDTOBuilder {
    private var httpStatus: HttpStatus? = null
    private var code: String? = null
    private var title: String? = null

    fun withCode(code: String?): ErrorResponseDTOBuilder {
        this.code = code
        return this
    }

    fun withTitle(title: String?): ErrorResponseDTOBuilder {
        this.title = title
        return this
    }

    fun withHttpStatus(httpStatus: HttpStatus?): ErrorResponseDTOBuilder {
        this.httpStatus = httpStatus
        return this
    }

    fun build(): ErrorResponseDTO {
        return if (title == null || httpStatus == null || code == null) {
            throw IllegalStateException("All fields must be defined")
        } else {
            val errorResponse = ErrorResponseDTO(httpStatus!!.value().toString(), this.code!!, this.title!!)
            this.title = null
            this.code = null
            this.httpStatus =null
            errorResponse
        }
    }

}