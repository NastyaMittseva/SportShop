package com.example.shop.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.shop.domain.ViewedProductDao

class ViewedProductDaoImpl(
    private val sharedPreferences: SharedPreferences
): ViewedProductDao {

    private var savedProductIds:List<Long>
        get() = sharedPreferences.getString(PRODUCT_TAG,null)?.split(",")
            ?.mapNotNull { it.toLongOrNull() } ?: emptyList()

        set(value) = sharedPreferences.edit {
            putString(PRODUCT_TAG, value.joinToString(","))
        }

    override fun addProduct(productID: Long) {
        val productIds: List<Long> = savedProductIds
        val newProductIds = mutableListOf<Long>().apply {
            add(productID)
            addAll(productIds.filter { it != productID })
        }
        savedProductIds = newProductIds
    }

    override fun getAllProducts(): List<Long> {
        return savedProductIds
    }

    companion object{
        private const val PRODUCT_TAG = "PRODUCT_TAG"
    }
}