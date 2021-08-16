package com.example.kotlin.Product.controller.v1

import com.example.kotlin.Product.dto.OwnerDTO
import com.example.kotlin.Product.service.IService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/owner")
class OwnerController(@Autowired
@Qualifier("ownerService") private val ownerService:IService<OwnerDTO>)
{
    @GetMapping()
    fun findAll():List<OwnerDTO> = ownerService.findAll()


    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id:Number):OwnerDTO = ownerService.findById(id)

}