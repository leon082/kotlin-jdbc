package com.example.kotlin.Product.dto

import java.security.acl.Owner

data class ProductDTO(
    val id:Number,
    val name:String,
    val price:Number,
    val owner:OwnerDTO
)
