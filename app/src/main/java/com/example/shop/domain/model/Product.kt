package com.example.shop.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Product(
    val id: String,
    val name: String,
    val price: Double,
    val discountPercent: Int,
    val description: String,
    val imageUrl: String,
    val categoryId: String
): Parcelable {
    fun calcDiscountPrice(): Double = price * (1 - discountPercent / 100.0)
    fun calcSavingMoney(): Double = price - calcDiscountPrice()
}