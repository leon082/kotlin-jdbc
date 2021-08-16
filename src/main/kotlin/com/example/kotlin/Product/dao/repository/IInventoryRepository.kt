package com.example.kotlin.Product.dao.repository

import com.example.kotlin.Product.dao.model.Product

interface IInventoryRepository {
    fun findInventory(id: Number): List<Product>
}