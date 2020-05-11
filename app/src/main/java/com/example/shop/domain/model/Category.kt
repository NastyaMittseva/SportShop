package com.example.shop.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Category (
    val id: String,
    val name: String
): Parcelable {

}