package com.example.kotlin.Product.controller.v1

import com.example.kotlin.Product.dto.ProductDTO
import com.example.kotlin.Product.service.IService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/product")
class ProductController(
    @Autowired
    @Qualifier("productService") private val productService: IService<ProductDTO>
) {

    @ApiOperation(value = "Gets all the products with its owner information.")
    @GetMapping()
    fun findAll(): List<ProductDTO> = productService.findAll()

    @ApiOperation(value = "Gets an specific product, with its owner info.")
    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Number): ProductDTO = productService.findById(id)

    @ApiOperation(value = "Save a product.")
    @PostMapping()
    fun save(@RequestBody productDTO: ProductDTO): Boolean = productService.save(productDTO)

    @ApiOperation(value = "Update a product.")
    @PutMapping()
    fun update(@RequestBody() productDTO: ProductDTO): ProductDTO = productService.update(productDTO)

    @ApiOperation(value = "Delete a product.")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Number): Boolean = productService.delete(id)

}