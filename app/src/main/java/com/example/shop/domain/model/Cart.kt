package com.example.shop.domain.model

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

    val discountSum get() = productList.map {product -> product.discountPercent}.sum()
    val fullSum get() = productList.map {product -> product.price}.sum()

}