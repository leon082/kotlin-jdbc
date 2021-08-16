package com.example.kotlin.Product.service.impl

import com.example.kotlin.Product.dto.ProductDTO
import com.example.kotlin.Product.dao.model.Product
import com.example.kotlin.Product.dao.repository.IRepository
import com.example.kotlin.Product.dto.OwnerDTO
import com.example.kotlin.Product.mapper.ProductMapper
import com.example.kotlin.Product.service.IService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
@Qualifier("productService")
class ProductService(
    @Autowired @Qualifier("productRepository") private val productRepository: IRepository<Product>,
    @Autowired @Qualifier("ownerService") private val ownerService: IService<OwnerDTO>,
    private val productMapper: ProductMapper
) : IService<ProductDTO> {


    override fun findAll(): List<ProductDTO> =
        productRepository.findAll().map { productMapper.mapperProductDTO(it, findOwnerDTO(it.owner)) }

    override fun findById(id: Number): ProductDTO =
        productRepository.findById(id).let { productMapper.mapperProductDTO(it, findOwnerDTO(it.owner)) }


    override fun save(t: ProductDTO): Boolean = productRepository.save(productMapper.mapperProduct(t))

    override fun update(t: ProductDTO): ProductDTO =
        productRepository.update(productMapper.mapperProduct(t))
            .let { productMapper.mapperProductDTO(it, findOwnerDTO(it.owner)) }

    override fun delete(id: Number): Boolean = productRepository.delete(id)


    private fun findOwnerDTO(id: Number): OwnerDTO = ownerService.findById(id)
}