package com.example.shop.domain

import retrofit2.http.GET
import retrofit2.http.Path

data class RemoteProduct(
    val id: String,
    val name: String,
    val price: Double,
    val discountPercent: Int,
    val description: String,
    val imageUrl: String,
    val category: String
)

data class RemoteCategory(
    val id: String,
    val name: String
)

interface MainApi {
    @GET("products/all/{author}")
    suspend fun allProducts(@Path("author") author: String):List<RemoteProduct>

    @GET("categories/all/{author}")
    suspend fun allCategories(@Path("author") author: String):List<RemoteCategory>
}