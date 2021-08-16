package com.example.kotlin.Product.service

import com.example.kotlin.Product.dto.InventoryDTO

interface IInventoryService {
    fun getInventory(owner: Number): InventoryDTO
}