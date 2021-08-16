package com.example.kotlin.Product.dto

data class ProductDTO(
    val id: Number?,
    val name: String,
    val price: Number,
    val owner: OwnerDTO
)
