package com.example.shop.presenter

import android.util.Log
import com.example.shop.di.modules.CartModule
import com.example.shop.domain.ViewedProductDao
import com.example.shop.domain.model.Cart
import com.example.shop.domain.model.Product
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

class ProductPresenter @Inject constructor(
    private val cart: Cart,
    private val viewedProductDao: ViewedProductDao
): MvpPresenter<ProductView>()  {

    fun onProductShow(product: Product) {
        viewedProductDao.addProduct(product.id)
    }

    fun addProductToCart(product: Product){
        cart.addProduct(product)
        Log.d("cartProducts ", cart.productList.toString())
    }
}