package com.example.kotlin.Product.dao.repository.impl

import com.example.kotlin.Product.dao.model.Product
import com.example.kotlin.Product.dao.model.mapper.ProductRowMapper
import com.example.kotlin.Product.dao.repository.IInventoryRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@PropertySource(ignoreResourceNotFound = true, value = arrayOf("classpath:queries/inventory-sql.properties"))
@Repository
class InventoryRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) : IInventoryRepository {

    @Value("\${inventory.find-inventory}")
    lateinit var QUERY_FIND_INVENTORY: String

    override fun findInventory(id: Number): List<Product> = try {
        jdbcTemplate.query(QUERY_FIND_INVENTORY, MapSqlParameterSource().addValue("owner", id), ProductRowMapper())
    } catch (e: Exception) {
        print(e.message)
        throw InternalError("ERROR Finding INVENTORY")
    }

}