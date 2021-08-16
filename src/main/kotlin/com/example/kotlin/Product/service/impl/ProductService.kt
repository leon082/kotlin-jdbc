package com.example.kotlin.Product.service.impl

import com.example.kotlin.Product.dto.ProductDTO
import com.example.kotlin.Product.dao.model.Product
import com.example.kotlin.Product.dao.repository.IRepository
import com.example.kotlin.Product.service.IService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier

class ProductService(@Autowired @Qualifier("productRepository") private val productRepository: IRepository<Product>) : IService<ProductDTO> {

    override fun findAll(): List<ProductDTO> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Number): ProductDTO {
        TODO("Not yet implemented")
    }

    override fun save(t: ProductDTO): ProductDTO {
        TODO("Not yet implemented")
    }

    override fun update(t: ProductDTO): ProductDTO {
        TODO("Not yet implemented")
    }

    override fun delete(id: Number): Boolean {
        TODO("Not yet implemented")
    }
}