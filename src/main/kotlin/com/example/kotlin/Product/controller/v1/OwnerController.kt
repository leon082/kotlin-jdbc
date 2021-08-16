package com.example.kotlin.Product.controller.v1

import com.example.kotlin.Product.dto.OwnerDTO
import com.example.kotlin.Product.service.IService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/owner")
class OwnerController(
    @Autowired
    @Qualifier("ownerService") private val ownerService: IService<OwnerDTO>
) {
    @ApiOperation(value = "Gets All Owners")
    @GetMapping()
    fun findAll(): List<OwnerDTO> = ownerService.findAll()

    @ApiOperation(value = "Gets an specific owner using the id.")
    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Number): OwnerDTO = ownerService.findById(id)

    @ApiOperation(value = "Saves an owner.")
    @PostMapping()
    fun save(@RequestBody ownerDTO: OwnerDTO): Boolean = ownerService.save(ownerDTO)

    @ApiOperation(value = "Updates an owner.")
    @PutMapping()
    fun update(@RequestBody() ownerDTO: OwnerDTO): OwnerDTO = ownerService.update(ownerDTO)

    @ApiOperation(value = "Delete an owner using the id.")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Number): Boolean = ownerService.delete(id)

}