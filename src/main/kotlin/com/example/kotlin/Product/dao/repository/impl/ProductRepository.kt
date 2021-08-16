package com.example.kotlin.Product.dao.repository.impl

import com.example.kotlin.Product.dao.model.Product
import com.example.kotlin.Product.dao.repository.IRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository

@Repository
@Qualifier("productRepository")
class ProductRepository: IRepository<Product> {
    override fun findAll(): List<Product> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Number): Product {
        TODO("Not yet implemented")
    }

    override fun save(t: Product): Product {
        TODO("Not yet implemented")
    }

    override fun update(t: Product): Product {
        TODO("Not yet implemented")
    }

    override fun delete(id: Number): Boolean {
        TODO("Not yet implemented")
    }
}