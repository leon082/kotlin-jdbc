package com.example.kotlin.Product.mapper

import com.example.kotlin.Product.dao.model.Owner
import com.example.kotlin.Product.dto.OwnerDTO
import org.springframework.stereotype.Component

@Component
class OwnerMapper {

    fun mapperOwnerDTO(owner: Owner): OwnerDTO = OwnerDTO(
        owner.id,
        owner.name,
        owner.age,
        owner.contactNumber
    )

    fun mapperOwner(ownerDTO: OwnerDTO): Owner = Owner(
        ownerDTO.id,
        ownerDTO.name,
        ownerDTO.age,
        ownerDTO.contactNumber
    )
}