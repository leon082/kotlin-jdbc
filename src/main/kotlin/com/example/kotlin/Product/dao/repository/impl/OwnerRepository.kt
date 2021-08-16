package com.example.kotlin.Product.dao.repository.impl

import com.example.kotlin.Product.dao.model.Owner
import com.example.kotlin.Product.dao.model.mapper.OwnerMapper
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


    override fun findAll(): List<Owner> {
        return try {
            jdbcTemplate.query(QUERY_FINDALL, OwnerMapper())
        } catch (e: Exception) {
            print(e.message)
            throw InternalError("ERROR Finding ALL")
        }
    }


    override fun findById(id: Number): Owner {
     return try {
         jdbcTemplate.query(QUERY_FIND_BY_ID, MapSqlParameterSource().addValue("id", id), OwnerMapper()).first()
     } catch (e: Exception) {
        print(e.message)
         throw InternalError("ERROR Finding BY ID")
     }
    }

    override fun save(t: Owner): Owner {
        TODO("Not yet implemented")
    }

    override fun update(t: Owner): Owner {
        TODO("Not yet implemented")
    }

    override fun delete(id: Number): Boolean {
        TODO("Not yet implemented")
    }
}