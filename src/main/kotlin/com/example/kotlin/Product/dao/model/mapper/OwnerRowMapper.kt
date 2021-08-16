package com.example.kotlin.Product.dao.model.mapper

import com.example.kotlin.Product.dao.model.Owner
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class OwnerRowMapper : RowMapper<Owner> {

    override fun mapRow(rs: ResultSet, rowNum: Int): Owner {
        return Owner(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("age"),
            rs.getString("contactNumber")
        )
    }
}
