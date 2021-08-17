package com.example.kotlin.Product.service

import com.example.kotlin.Product.dao.model.Product
import com.example.kotlin.Product.dao.repository.impl.InventoryRepository
import com.example.kotlin.Product.dto.OwnerDTO
import com.example.kotlin.Product.dto.ProductDTO
import com.example.kotlin.Product.mapper.ProductMapper
import com.example.kotlin.Product.service.impl.InventoryService
import com.example.kotlin.Product.service.impl.OwnerService
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.*

class InventoryServiceSpec : FunSpec() {
    init {
        val productMapper = mockk<ProductMapper>()
        val inventoryRepository = mockk<InventoryRepository>()
        val ownerService = mockk<OwnerService>()
        val service = InventoryService(ownerService, inventoryRepository, productMapper)

        test("find inventory sucessfull") {
            every { productMapper.mapperProduct(any()) } returns aValidProduct()
            every { productMapper.mapperProductDTO(any(), any()) } returns aValidProductDTO()
            every { ownerService.findById(any()) } returns aValidOwnerDTO()
            every { inventoryRepository.findInventory(any()) } returns listOf(aValidProduct())
            val inventory = service.getInventory(1)
            inventory.owner shouldBe aValidOwnerDTO()
            inventory.product.size shouldBe 1

        }
    }

    private fun aValidProduct(): Product = Product(1, "iPhone", 50000, 1)
    private fun aValidProductDTO(): ProductDTO = ProductDTO(1, "iPhone", 50000, aValidOwnerDTO())
    private fun aValidOwnerDTO(): OwnerDTO = OwnerDTO(1, "Leon", "27", "3168299435")
}