package com.example.kotlin.Product.service

import com.example.kotlin.Product.dao.repository.impl.ProductRepository
import com.example.kotlin.Product.mapper.ProductMapper
import com.example.kotlin.Product.service.impl.OwnerService
import com.example.kotlin.Product.service.impl.ProductService
import com.example.kotlin.Product.utils.BuildObject
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ProductServiceSpec : FunSpec() {
    init {
        val productMapper = ProductMapper()
        val productRepository = mockk<ProductRepository>()
        val ownerService = mockk<OwnerService>()
        val service = ProductService(productRepository, ownerService, productMapper)
        val buildObject = BuildObject()
        every { ownerService.findById(any()) } returns buildObject.aValidOwnerDTO()

        test("find product by id successful") {

            every { productRepository.findById(any()) } returns buildObject.aValidProduct()
            service.findById(1) shouldBe buildObject.aValidProductDTO()

        }
        test("find all products successful") {

            every { productRepository.findAll() } returns listOf(buildObject.aValidProduct())
            val listProducts = service.findAll()
            listProducts.size shouldBe 1
            listProducts.get(0) shouldBe buildObject.aValidProductDTO()


        }
        test("update product successful") {

            every { productRepository.update(any()) } returns buildObject.aValidProduct()
            service.update(buildObject.aValidProductDTO()) shouldBe buildObject.aValidProductDTO()

        }
        test("save product successful") {

            every { productRepository.save(any()) } returns true

            service.save(buildObject.aValidProductDTO()) shouldBe true

        }
        test("delete product by id successful") {

            every { productRepository.delete(any()) } returns true
            service.delete(1) shouldBe true

        }
    }


}