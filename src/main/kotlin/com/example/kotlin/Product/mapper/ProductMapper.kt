package com.example.kotlin.Product.mapper

import com.example.kotlin.Product.dao.model.Product
import com.example.kotlin.Product.dto.OwnerDTO
import com.example.kotlin.Product.dto.ProductDTO
import org.springframework.stereotype.Component

@Component
class ProductMapper {

    fun mapperProductDTO(product: Product, ownerDTO: OwnerDTO): ProductDTO = ProductDTO(
        product.id,
        product.name,
        product.price,
        ownerDTO
    )

    fun mapperProduct(productDTO: ProductDTO): Product = Product(
        productDTO.id,
        productDTO.name,
        productDTO.price,
        productDTO.owner.id!!
    )
}