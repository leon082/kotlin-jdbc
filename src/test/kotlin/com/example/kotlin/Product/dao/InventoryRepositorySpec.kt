package com.example.kotlin.Product.dao

import com.example.kotlin.Product.dao.model.mapper.ProductRowMapper
import com.example.kotlin.Product.dao.repository.impl.InventoryRepository
import com.example.kotlin.Product.utils.BuildObject
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class InventoryRepositorySpec : FunSpec() {
    init {
        val jdbcTemplate = mockk<NamedParameterJdbcTemplate>()
        val repository = InventoryRepository(jdbcTemplate)
        val buildObject = BuildObject()

        test("find inventory successful") {

            every {
                jdbcTemplate.query(
                    any<String>(),
                    ofType<MapSqlParameterSource>(),
                    ofType<ProductRowMapper>()
                )
            } returns listOf(buildObject.aValidProduct())
            val inventory = repository.findInventory(1)
            inventory.size shouldBe 1

        }
    }


}