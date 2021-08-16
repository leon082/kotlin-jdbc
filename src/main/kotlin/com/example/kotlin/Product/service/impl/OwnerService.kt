package com.example.kotlin.Product.service.impl

import com.example.kotlin.Product.dto.OwnerDTO
import com.example.kotlin.Product.dao.model.Owner
import com.example.kotlin.Product.dao.repository.IRepository
import com.example.kotlin.Product.mapper.OwnerMapper
import com.example.kotlin.Product.service.IService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service


@Service
@Qualifier("ownerService")
class OwnerService(
    @Autowired @Qualifier("ownerRepository") private val ownerRepository: IRepository<Owner>,
    private val ownerMapper: OwnerMapper
) :
    IService<OwnerDTO> {


    override fun findAll(): List<OwnerDTO> = ownerRepository.findAll().map { ownerMapper.mapperOwnerDTO(it) }

    override fun findById(id: Number): OwnerDTO = ownerRepository.findById(id).let { ownerMapper.mapperOwnerDTO(it) }


    override fun save(t: OwnerDTO): Boolean = ownerRepository.save(ownerMapper.mapperOwner(t))

    override fun update(t: OwnerDTO): OwnerDTO =
        ownerRepository.update(ownerMapper.mapperOwner(t)).let { ownerMapper.mapperOwnerDTO(it) }

    override fun delete(id: Number): Boolean = ownerRepository.delete(id)


}