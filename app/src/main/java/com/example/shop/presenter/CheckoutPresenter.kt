package com.example.shop.presenter

import com.example.shop.domain.model.Cart
import com.example.shop.domain.model.Order
import moxy.MvpPresenter
import javax.inject.Inject

class CheckoutPresenter @Inject constructor(
    private val cart: Cart
) : BasePresenter<CheckoutView>() {

    private val model = Order()

    fun getInitialPrice(): Int = cart.initialSum

    fun getSavingMoney():Int = cart.savingSum

    fun getTotalPrice(): Int = cart.totalSum

    fun checkPersonName(text: String) {
        if (!checkSymbols(text, 3)) model.personName= text
        viewState.showErrorForPersonName(checkSymbols(text, 3))
    }

    fun checkPhone(text: String) {
        if (!checkStringPhone(text)) model.personPhone = text
        viewState.showErrorForPersonPhone(checkStringPhone(text))
    }

    fun checkEmail(text: String) {
        if (!(checkStringEmail(text) or checkSymbols(text, 7))) model.personEmail = text
        viewState.showErrorForPersonEmail(checkStringEmail(text) or checkSymbols(text, 7))
    }

    private fun checkSymbols(text: String, lgth: Int): Boolean = text.length < lgth

    private fun checkStringPhone(text: String): Boolean = !(Regex("(\\+7|8)\\d{10}").matches(text))

    private fun checkStringEmail(text: String): Boolean = !("@" in text)

}