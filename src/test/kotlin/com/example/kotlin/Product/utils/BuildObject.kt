package com.example.kotlin.Product.utils

import com.example.kotlin.Product.dao.model.Owner
import com.example.kotlin.Product.dao.model.Product
import com.example.kotlin.Product.dto.InventoryDTO
import com.example.kotlin.Product.dto.OwnerDTO
import com.example.kotlin.Product.dto.ProductDTO

class BuildObject {

    fun aValidProduct(): Product = Product(1, "iPhone", 50000, 1)
    fun aValidProductDTO(): ProductDTO = ProductDTO(1, "iPhone", 50000, this.aValidOwnerDTO())
    fun aValidOwner(): Owner = Owner(1, "iPhone", "27", "3162875498")
    fun aValidOwnerDTO(): OwnerDTO = OwnerDTO(1, "iPhone", "27", "3162875498")
    fun aValidInventoryDTO(): InventoryDTO = InventoryDTO(this.aValidOwnerDTO(), listOf(this.aValidProductDTO()))
    fun aValidJsonOwnerDTO(): String {
        return """{                   
                    "name": "Jairo",
                    "age" : "21",
                    "contact_number": "3136631528"}
                }
            """.trimIndent()
    }
}