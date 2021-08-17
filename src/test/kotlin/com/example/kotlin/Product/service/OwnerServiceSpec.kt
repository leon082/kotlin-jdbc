package com.example.kotlin.Product.service

import com.example.kotlin.Product.dao.repository.impl.OwnerRepository
import com.example.kotlin.Product.mapper.OwnerMapper
import com.example.kotlin.Product.service.impl.OwnerService
import com.example.kotlin.Product.utils.BuildObject
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class OwnerServiceSpec : FunSpec() {
    init {
        val ownerMapper = OwnerMapper()
        val ownerRepository = mockk<OwnerRepository>()
        val service = OwnerService(ownerRepository, ownerMapper)
        val buildObject = BuildObject()

        test("find owner by id successful") {

            every { ownerRepository.findById(any()) } returns buildObject.aValidOwner()
            service.findById(1) shouldBe buildObject.aValidOwnerDTO()

        }
        test("find all owners successful") {

            every { ownerRepository.findAll() } returns listOf(buildObject.aValidOwner())
            service.findAll().size shouldBe 1


        }
        test("update owner successful") {

            every { ownerRepository.update(any()) } returns buildObject.aValidOwner()
            service.update(buildObject.aValidOwnerDTO()) shouldBe buildObject.aValidOwnerDTO()

        }
        test("save owner successful") {

            every { ownerRepository.save(any()) } returns true

            service.save(buildObject.aValidOwnerDTO()) shouldBe true

        }
        test("delete owner by id successful") {

            every { ownerRepository.delete(any()) } returns true
            service.delete(1) shouldBe true

        }
    }


}