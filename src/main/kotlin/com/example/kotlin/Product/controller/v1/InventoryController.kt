package com.example.kotlin.Product.controller.v1

import com.example.kotlin.Product.dto.InventoryDTO
import com.example.kotlin.Product.service.IInventoryService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/inventory")
class InventoryController(private val inventoryService: IInventoryService) {

    @ApiOperation(value = "Gets inventory the products by owner, with all the products info.")
    @GetMapping()
    fun findInventory(@RequestParam("ownerId") ownerId: Number): InventoryDTO = inventoryService.getInventory(ownerId)
}