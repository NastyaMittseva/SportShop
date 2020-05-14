package com.example.shop.domain.model

import kotlin.math.roundToInt

class Cart() {
    var productList = mutableListOf<Product>()

    fun addProduct(product: Product){
        productList.add(product)
    }

    fun deleteProduct(product: Product){
        productList.remove(product)
    }

    fun updateProducts(products: List<Product>){
        productList = products.toMutableList()
    }

    fun getCountProducts() = productList.count()
    val totalSum get() = productList.map {product -> product.calcDiscountPrice()}.sum().roundToInt()
    val initialSum get() = productList.map {product -> product.price}.sum().roundToInt()
    val savingSum get() = totalSum - initialSum
}