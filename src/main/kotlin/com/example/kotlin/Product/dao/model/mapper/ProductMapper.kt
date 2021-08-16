package com.example.kotlin.Product.dao.model.mapper

import com.example.kotlin.Product.dao.model.Product
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet


class ProductMapper : RowMapper<Product> {

    override fun mapRow(rs: ResultSet, rowNum: Int): Product {
        return Product(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getDouble("price"),
            rs.getLong("owner")
        )
    }
}