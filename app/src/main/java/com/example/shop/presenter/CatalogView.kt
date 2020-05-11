package com.example.shop.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CatalogView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCategoryNames(list: List<String>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError(error: String)
}