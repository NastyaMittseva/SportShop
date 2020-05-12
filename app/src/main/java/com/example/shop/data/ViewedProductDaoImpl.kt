package com.example.shop.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.shop.domain.ViewedProductDao

class ViewedProductDaoImpl(
    private val sharedPreferences: SharedPreferences
): ViewedProductDao {

    private var savedProductIds:List<String>
        get() = sharedPreferences.getString(PRODUCT_TAG,null)?.split(",")
            ?.mapNotNull { it } ?: emptyList()

        set(value) = sharedPreferences.edit {
            putString(PRODUCT_TAG, value.joinToString(","))
        }

    override fun addProduct(productID: String) {
        val productIds: List<String> = savedProductIds
        val newProductIds = mutableListOf<String>().apply {
            add(productID)
            addAll(productIds.filter { it != productID })
        }
        savedProductIds = newProductIds
    }

    override fun getAllProducts(): List<String> {
        return savedProductIds
    }

    companion object{
        private const val PRODUCT_TAG = "PRODUCT_TAG"
    }
}