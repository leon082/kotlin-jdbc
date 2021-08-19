package com.example.kotlin.Product.dao.repository.impl

import com.example.kotlin.Product.dao.model.Owner
import com.example.kotlin.Product.dao.model.mapper.OwnerRowMapper
import com.example.kotlin.Product.dao.repository.IRepository
import com.example.kotlin.Product.exception.GenericError
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@PropertySource(ignoreResourceNotFound = true, value = arrayOf("classpath:queries/owner-sql.properties"))
@Repository
@Qualifier("ownerRepository")
class OwnerRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) : IRepository<Owner> {

    @Value("\${owner.find-all}")
    var QUERY_FINDALL: String = ""

    @Value("\${owner.find-by-id}")
    var QUERY_FIND_BY_ID: String = ""

    @Value("\${owner.save}")
    var QUERY_SAVE: String = ""

    @Value("\${owner.update}")
    var QUERY_UPDATE: String = ""

    @Value("\${owner.delete}")
    var QUERY_DELET: String = ""


    override fun findAll(): List<Owner> = try {
        jdbcTemplate.query(QUERY_FINDALL, OwnerRowMapper())
    } catch (e: DataAccessException) {
        throw GenericError("400","ERROR FINDING ALL")
    }catch(e: Exception){
        throw Exception("ERROR FINDING ALL")
    }


    override fun findById(id: Number): Owner = try {
        jdbcTemplate.query(QUERY_FIND_BY_ID, MapSqlParameterSource().addValue("id", id), OwnerRowMapper()).first()
    } catch (e: DataAccessException) {
        throw GenericError("404","ERROR, ID NOT VALID")
    }catch (e: NoSuchElementException) {
        print("NoSuchElementException...")
        throw GenericError("404","ERROR, ID NOT VALID")
    }catch(e: Exception){
        print(e)
        throw Exception("ERROR FINDING BY ID")
    }

    override fun save(t: Owner): Boolean = try {
        val params = MapSqlParameterSource()
        params.addValue("name", t.name)
        params.addValue("age", t.age)
        params.addValue("contactNumber", t.contactNumber)

        jdbcTemplate.update(QUERY_SAVE, params) > 0
    } catch (e: Exception) {
        print(e.message)
        throw Exception("ERROR SAVING")
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
            throw Exception("ERROR UPDATING")
        }
    }

    override fun delete(id: Number): Boolean = try {

        jdbcTemplate.update(QUERY_DELET, MapSqlParameterSource().addValue("id", id)) > 0

    } catch (e: Exception) {
        print(e.message)
        throw Exception("ERROR DELETING BY ID")
    }
}