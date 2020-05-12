package com.example.shop.presenter

import com.example.shop.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CategoryView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProducts(list: List<Product>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProductInfo(product: Product)
}