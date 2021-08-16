package com.example.kotlin.Product.service

interface IService<T> {
    fun findAll():List<T>
    fun findById(id:Number):T
    fun save(t:T):T
    fun update(t:T):T
    fun delete(id:Number):Boolean

}