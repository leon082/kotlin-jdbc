package com.example.kotlin.Product.dto

data class ErrorResponseDTO(
    val status: String,
    val code: String,
    val title: String
)