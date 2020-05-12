package com.example.shop.presenter

import com.example.shop.domain.model.Cart
import com.example.shop.domain.model.Product
import moxy.MvpPresenter
import javax.inject.Inject

class CartPresenter @Inject constructor(
    private val cart: Cart
    ): BasePresenter<CartView>() {

    fun setItems(){
        viewState.setItems(cart)
    }

    fun removeItem(product: Product){
        val position = cart.productList.indexOf(product)
        cart.deleteProduct(product)
        viewState.removeItem(position)
    }

    fun showProductDetails(product: Product) {
        viewState.showProductDetails(product)
    }
}