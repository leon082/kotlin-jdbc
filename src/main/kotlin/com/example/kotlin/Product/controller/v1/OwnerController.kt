package com.example.kotlin.Product.controller.v1

import com.example.kotlin.Product.dto.OwnerDTO
import com.example.kotlin.Product.service.IService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/owner")
class OwnerController(
    @Autowired
    @Qualifier("ownerService") private val ownerService: IService<OwnerDTO>
) {
    @GetMapping()
    fun findAll(): List<OwnerDTO> = ownerService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Number): OwnerDTO = ownerService.findById(id)

    @PostMapping()
    fun save(@RequestBody ownerDTO: OwnerDTO): Boolean = ownerService.save(ownerDTO)

    @PutMapping()
    fun findById(@RequestBody() ownerDTO: OwnerDTO): OwnerDTO = ownerService.update(ownerDTO)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Number): Boolean = ownerService.delete(id)

}