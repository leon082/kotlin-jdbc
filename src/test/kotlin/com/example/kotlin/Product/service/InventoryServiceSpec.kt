package com.example.kotlin.Product.service

import com.example.kotlin.Product.dao.repository.impl.InventoryRepository
import com.example.kotlin.Product.mapper.ProductMapper
import com.example.kotlin.Product.service.impl.InventoryService
import com.example.kotlin.Product.service.impl.OwnerService
import com.example.kotlin.Product.utils.BuildObject
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.*

class InventoryServiceSpec : FunSpec() {
    init {
        val productMapper = ProductMapper()
        val inventoryRepository = mockk<InventoryRepository>()
        val ownerService = mockk<OwnerService>()
        val service = InventoryService(ownerService, inventoryRepository, productMapper)
        val buildObject = BuildObject()

        test("find inventory successful") {

            every { ownerService.findById(any()) } returns buildObject.aValidOwnerDTO()
            every { inventoryRepository.findInventory(any()) } returns listOf(buildObject.aValidProduct())
            val inventory = service.getInventory(1)
            inventory.owner shouldBe buildObject.aValidOwnerDTO()
            inventory.product.size shouldBe 1

        }
    }


}