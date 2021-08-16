package com.example.kotlin.Product.service

import com.example.kotlin.Product.dto.InventoryDTO

interface IInventory {
    fun getInventory(owner: Number): InventoryDTO
}