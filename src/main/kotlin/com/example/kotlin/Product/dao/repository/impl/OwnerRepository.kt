package com.example.kotlin.Product.dao.repository.impl

import com.example.kotlin.Product.dao.model.Owner
import com.example.kotlin.Product.dao.model.mapper.OwnerRowMapper
import com.example.kotlin.Product.dao.repository.IRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@PropertySource(ignoreResourceNotFound = true, value = arrayOf("classpath:queries/owner-sql.properties"))
@Repository
@Qualifier("ownerRepository")
class OwnerRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) : IRepository<Owner> {

    @Value("\${owner.find-all}")
    lateinit var QUERY_FINDALL: String

    @Value("\${owner.find-by-id}")
    lateinit var QUERY_FIND_BY_ID: String

    @Value("\${owner.save}")
    lateinit var QUERY_SAVE: String

    @Value("\${owner.update}")
    lateinit var QUERY_UPDATE: String

    @Value("\${owner.delete}")
    lateinit var QUERY_DELET: String


    override fun findAll(): List<Owner> = try {
        jdbcTemplate.query(QUERY_FINDALL, OwnerRowMapper())
    } catch (e: Exception) {
        print(e.message)
        throw InternalError("ERROR Finding ALL")
    }


    override fun findById(id: Number): Owner = try {
        jdbcTemplate.query(QUERY_FIND_BY_ID, MapSqlParameterSource().addValue("id", id), OwnerRowMapper()).first()
    } catch (e: Exception) {
        print(e.message)
        throw InternalError("ERROR Finding BY ID")
    }

    override fun save(t: Owner): Boolean = try {
        val params = MapSqlParameterSource()
        params.addValue("name", t.name)
        params.addValue("age", t.age)
        params.addValue("contactNumber", t.contactNumber)

        jdbcTemplate.update(QUERY_SAVE, params) > 0
    } catch (e: Exception) {
        print(e.message)
        throw InternalError("ERROR SAVING")
    }

    override fun update(t: Owner): Owner {
        return try {
            val params = MapSqlParameterSource()
            params.addValue("name", t.name)
            params.addValue("age", t.age)
            params.addValue("contactNumber", t.contactNumber)
            params.addValue("id", t.id)

            jdbcTemplate.update(QUERY_UPDATE, params)
            return t
        } catch (e: Exception) {
            print(e.message)
            throw InternalError("ERROR UPDATING")
        }
    }

    override fun delete(id: Number): Boolean = try {

        jdbcTemplate.update(QUERY_DELET, MapSqlParameterSource().addValue("id", id)) > 0

    } catch (e: Exception) {
        print(e.message)
        throw InternalError("ERROR DELETING BY ID")
    }
}