package com.example.shop.domain

interface ViewedProductDao {
    fun addProduct(productID: Long)
    fun getAllProducts():List<Long>
}