package com.example.kotlin.Product.dao.repository.impl

import com.example.kotlin.Product.dao.model.Product
import com.example.kotlin.Product.dao.model.mapper.ProductRowMapper
import com.example.kotlin.Product.dao.repository.IInventoryRepository
import com.example.kotlin.Product.exception.GenericError
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@PropertySource(ignoreResourceNotFound = true, value = arrayOf("classpath:queries/inventory-sql.properties"))
@Repository
class InventoryRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) : IInventoryRepository {

    @Value("\${inventory.find-inventory}")
    var QUERY_FIND_INVENTORY: String = ""

    override fun findInventory(id: Number): List<Product> = try {
        jdbcTemplate.query(QUERY_FIND_INVENTORY, MapSqlParameterSource().addValue("owner", id), ProductRowMapper())
    } catch (e: EmptyResultDataAccessException) {
        print("EmptyResultDataAccessException...")
        throw GenericError("404","ERROR,ID NOT VALID")
    }catch(e: Exception){
        throw Exception("ERROR GETTING INVENTORY.")
    }

}