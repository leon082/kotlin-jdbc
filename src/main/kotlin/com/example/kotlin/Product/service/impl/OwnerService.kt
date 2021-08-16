package com.example.kotlin.Product.service.impl

import com.example.kotlin.Product.dto.OwnerDTO
import com.example.kotlin.Product.dao.model.Owner
import com.example.kotlin.Product.dao.repository.IRepository
import com.example.kotlin.Product.service.IService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service


@Service
@Qualifier("ownerService")
class OwnerService(@Autowired @Qualifier("ownerRepository") private val ownerRepository: IRepository<Owner>) :
    IService<OwnerDTO> {


    override fun findAll(): List<OwnerDTO> = ownerRepository.findAll().map{this.mapperOwnerDTO(it)}



    override fun findById(id: Number): OwnerDTO = ownerRepository.findById(id).let { this.mapperOwnerDTO(it) }


    override fun save(t: OwnerDTO): OwnerDTO {
        return OwnerDTO(1, "name", "30", "")
    }

    override fun update(t: OwnerDTO): OwnerDTO {
        return OwnerDTO(1, "name", "30", "")
    }

    override fun delete(id: Number): Boolean {
        return true
    }

    private fun mapperOwnerDTO(owner: Owner): OwnerDTO {
        return OwnerDTO(
            owner.id,
            owner.name,
            owner.age,
            owner.contactNumber
        )
    }


}