package com.example.kotlin.Product.dao.repository.impl


import com.example.kotlin.Product.dao.model.Product
import com.example.kotlin.Product.dao.model.mapper.ProductRowMapper
import com.example.kotlin.Product.dao.repository.IRepository
import com.example.kotlin.Product.exception.GenericError
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@PropertySource(ignoreResourceNotFound = true, value = arrayOf("classpath:queries/product-sql.properties"))
@Repository
@Qualifier("productRepository")
class ProductRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) : IRepository<Product> {

    @Value("\${product.find-all}")
    var QUERY_FINDALL: String = ""

    @Value("\${product.find-by-id}")
    var QUERY_FIND_BY_ID: String = ""

    @Value("\${product.save}")
    var QUERY_SAVE: String = ""

    @Value("\${product.update}")
    var QUERY_UPDATE: String = ""

    @Value("\${product.delete}")
    var QUERY_DELET: String = ""


    override fun findAll(): List<Product> = try {
        jdbcTemplate.query(QUERY_FINDALL, ProductRowMapper())
    } catch (e: DataAccessException) {
        throw GenericError("404","ERROR Finding ALL")
    }catch(e: Exception){
        throw Exception("ERROR FINDING ALL")
    }


    override fun findById(id: Number): Product = try {
        jdbcTemplate.query(QUERY_FIND_BY_ID, MapSqlParameterSource().addValue("id", id), ProductRowMapper()).first()
    } catch (e: DataAccessException) {
        throw GenericError("404","ERROR , ID NOT VALID")
    }catch (e: NoSuchElementException) {
        throw GenericError("404","ERROR, ID NOT VALID")
    }catch(e: Exception){
        throw Exception("ERROR FINDING BY ID")
    }

    override fun save(t: Product): Boolean = try {
        val params = MapSqlParameterSource()
        params.addValue("name", t.name)
        params.addValue("price", t.price)
        params.addValue("owner", t.owner)

        jdbcTemplate.update(QUERY_SAVE, params) > 0
    } catch (e: Exception) {
        print(e.message)
        throw InternalError("ERROR SAVING")
    }

    override fun update(t: Product): Product {
        return try {
            val params = MapSqlParameterSource()
            params.addValue("name", t.name)
            params.addValue("price", t.price)
            params.addValue("owner", t.owner)
            params.addValue("id", t.id)

            jdbcTemplate.update(QUERY_UPDATE, params)
            return t
        } catch (e: Exception) {
            print(e.message)
            throw InternalError("ERROR UPDATING BY ID")
        }
    }

    override fun delete(id: Number): Boolean = try {

        jdbcTemplate.update(QUERY_DELET, MapSqlParameterSource().addValue("id", id)) > 0

    } catch (e: Exception) {
        print(e.message)
        throw InternalError("ERROR DELETING BY ID")
    }
}