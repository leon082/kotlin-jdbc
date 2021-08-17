package com.example.kotlin.Product.dao

import com.example.kotlin.Product.dao.model.mapper.OwnerRowMapper
import com.example.kotlin.Product.dao.repository.impl.OwnerRepository
import com.example.kotlin.Product.utils.BuildObject
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class ProductRepositorySpec : FunSpec() {
    init {
        val jdbcTemplate = mockk<NamedParameterJdbcTemplate>()
        val repository = OwnerRepository(jdbcTemplate)
        val buildObject = BuildObject()

        every {
            jdbcTemplate.query(
                any<String>(),
                ofType<MapSqlParameterSource>(),
                ofType<OwnerRowMapper>()
            )
        } returns listOf(buildObject.aValidOwner())

        every {
            jdbcTemplate.update(
                any<String>(),
                ofType<MapSqlParameterSource>()
            )
        } returns 1


        test("find product by id successful") {


            repository.findById(1) shouldBe buildObject.aValidOwner()

        }

        test("update product successful") {


            repository.update(buildObject.aValidOwner()) shouldBe buildObject.aValidOwner()

        }
        test("save product successful") {


            repository.save(buildObject.aValidOwner()) shouldBe true

        }
        test("delete product by id successful") {


            repository.delete(1) shouldBe true

        }
        test("find all products successful") {
            every {
                jdbcTemplate.query(
                    any<String>(),
                    ofType<OwnerRowMapper>()
                )
            } returns listOf(buildObject.aValidOwner())

            repository.findAll().size shouldBe 1


        }
    }


}