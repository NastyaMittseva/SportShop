package com.example.shop.domain

interface CartDao {
    fun addProduct(productID: String)
    fun getAllProducts():List<String>
    fun deleteProduct(productID: String)
    fun clearCart()
}