package com.example.shop.domain

interface ViewedProductDao {
    fun addProduct(productID: String)
    fun getAllProducts():List<String>
    fun deleteProduct(productID: String)
}