package com.example.shop.presenter

import com.example.shop.domain.model.Category
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CatalogView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCategory(list: List<Category>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProductsInCategory(category: Category)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError(error: String)
}