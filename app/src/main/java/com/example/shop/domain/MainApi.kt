package com.example.shop.domain

import com.example.shop.domain.model.Order
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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
    @GET("products")
    suspend fun allProducts():List<RemoteProduct>

    @GET("categories")
    suspend fun allCategories():List<RemoteCategory>

    @POST("order")
    suspend fun createOrder(@Body request: Order): Long
}