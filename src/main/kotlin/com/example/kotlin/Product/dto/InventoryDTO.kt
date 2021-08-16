package com.example.kotlin.Product.dto

data class InventoryDTO(
    val owner: OwnerDTO,
    val product: List<ProductDTO>
)
