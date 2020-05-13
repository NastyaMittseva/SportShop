package com.example.shop.data

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.example.shop.di.modules.PreferencesModule
import  com.example.shop.domain.ViewedProductDao
import android.content.Context

class ViewedProductDaoImpl(
    private val sharedPreferences: SharedPreferences
): ViewedProductDao {

    private var savedProductIds:MutableList<String>
        get() = sharedPreferences.getString(PRODUCT_TAG,null)?.split(",")
            ?.mapNotNull { it }?.toMutableList() ?: mutableListOf()

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

    override fun deleteProduct(productID: String) {
        val productIds: MutableList<String> =  savedProductIds
        productIds.remove(productID)
        savedProductIds = productIds
    }

    companion object{
        private const val PRODUCT_TAG = "PRODUCT_TAG"
    }
}