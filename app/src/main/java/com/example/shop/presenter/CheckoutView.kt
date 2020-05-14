package com.example.shop.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CheckoutView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForPersonName(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForPersonPhone(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForPersonEmail(visible: Boolean)

}