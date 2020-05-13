package com.example.shop.presenter

import android.util.Log
import com.example.shop.domain.MainApi
import com.example.shop.domain.ViewedProductDao
import com.example.shop.domain.model.Cart
import com.example.shop.domain.model.Category
import com.example.shop.domain.model.Product
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import javax.inject.Inject

class CartPresenter @Inject constructor(
    private val cart: Cart,
    private val mainApi: MainApi,
    private val viewedProductDao: ViewedProductDao
    ): BasePresenter<CartView>() {

    fun setItems(){
        val productIds = viewedProductDao.getAllProducts()
        Log.d("productIds", productIds.toString())
        launch {
            Log.d("productIds ", productIds.toString())
            val remoteProducts = mainApi.allProducts("default")
                .filter { it.id in productIds }
                .map { it -> Product(it.id, it.name, it.price, it.discountPercent, it.description,
                    it.imageUrl, it.category) }
            cart.updateProducts(remoteProducts)
            viewState.setItems(cart)
        }
    }

    fun removeItem(product: Product){
        val position = cart.productList.indexOf(product)
        cart.deleteProduct(product)
        viewedProductDao.deleteProduct(product.id)
        viewState.removeItem(position)
    }

    fun showProductDetails(product: Product) {
        viewState.showProductDetails(product)
    }

}