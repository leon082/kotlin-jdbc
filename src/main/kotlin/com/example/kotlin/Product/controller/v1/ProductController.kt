package com.example.kotlin.Product.controller.v1

import com.example.kotlin.Product.dto.ProductDTO
import com.example.kotlin.Product.service.IService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/product")
class ProductController(
    @Autowired
    @Qualifier("productService") private val productService: IService<ProductDTO>
) {

    @GetMapping()
    fun findAll(): List<ProductDTO> = productService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Number): ProductDTO = productService.findById(id)

    @PostMapping()
    fun save(@RequestBody productDTO: ProductDTO): Boolean = productService.save(productDTO)

    @PutMapping()
    fun findById(@RequestBody() productDTO: ProductDTO): ProductDTO = productService.update(productDTO)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Number): Boolean = productService.delete(id)

}