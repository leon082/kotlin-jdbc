package com.example.kotlin.Product.dao.repository

interface IRepository<T> {
    fun findAll(): List<T>
    fun findById(id: Number): T
    fun save(t: T): Boolean
    fun update(t: T): T
    fun delete(id: Number): Boolean
}