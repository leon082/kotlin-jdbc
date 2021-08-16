package com.example.kotlin.Product.service.impl


import com.example.kotlin.Product.dao.repository.IInventoryRepository
import com.example.kotlin.Product.dto.InventoryDTO
import com.example.kotlin.Product.dto.OwnerDTO
import com.example.kotlin.Product.dto.ProductDTO
import com.example.kotlin.Product.mapper.ProductMapper
import com.example.kotlin.Product.service.IInventory
import com.example.kotlin.Product.service.IService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class InventoryImpl(
    @Autowired @Qualifier("ownerService") private val ownerService: IService<OwnerDTO>,
    @Autowired private val inventoryRepository: IInventoryRepository,
    private val productMapper: ProductMapper
) : IInventory {

    override fun getInventory(id: Number): InventoryDTO =
        ownerService.findById(id).let {
            this.buildInventoryDTO(it)
        }

    private fun buildInventoryDTO(ownerDTO: OwnerDTO): InventoryDTO = InventoryDTO(
        ownerDTO,
        getProducts(ownerDTO)
    )

    private fun getProducts(ownerDTO: OwnerDTO): List<ProductDTO> = inventoryRepository.findInventory(ownerDTO.id!!)
        .map { product -> productMapper.mapperProductDTO(product, ownerDTO) }

}