package com.example.shop.presenter

import com.example.shop.domain.model.Cart
import com.example.shop.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CartView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setItems(cart: Cart)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeItem(position: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProductDetails(product: Product)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun updateBasketState(active: Boolean)

}