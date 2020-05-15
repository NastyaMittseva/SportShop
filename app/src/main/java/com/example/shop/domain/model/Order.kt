package com.example.shop.domain.model

data class Order(
    var personName:String = "",
    var personPhone: String = "",
    var personEmail: String = "",
    var paymentType: String = "",
    var initialSumOrder: Int = 0,
    var discount: Int = 0,
    var totalSumOrder: Int = 0,
    var products: List<Product>
)