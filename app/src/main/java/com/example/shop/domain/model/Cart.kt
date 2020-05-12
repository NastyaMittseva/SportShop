package com.example.shop.domain.model

class Cart {
    val productList = mutableListOf<Product>()

    fun addProduct(product: Product){
        productList.add(product)
    }

    fun deleteProduct(product: Product){
        productList.remove(product)
    }

    val discountSum get() = productList.map {product -> product.discountPercent}.sum()
    val fullSum get() = productList.map {product -> product.price}.sum()

}