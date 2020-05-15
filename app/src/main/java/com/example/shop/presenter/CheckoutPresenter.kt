package com.example.shop.presenter

import android.util.Log
import android.widget.Toast
import com.example.shop.domain.MainApi
import com.example.shop.domain.interactor.ClearCartUseCase
import com.example.shop.domain.model.Cart
import com.example.shop.domain.model.Order
import kotlinx.coroutines.launch
import javax.inject.Inject

class CheckoutPresenter @Inject constructor(
    private val cart: Cart,
    private val api: MainApi,
    private val clearCartUseCase: ClearCartUseCase
) : BasePresenter<CheckoutView>() {

    private var order = Order(products = cart.productList)

    fun checkPersonName(text: String) {
        if (!checkSymbols(text, 3)) order.personName= text
        viewState.showErrorForPersonName(checkSymbols(text, 3))
    }

    fun checkPhone(text: String) {
        if (!checkStringPhone(text)) order.personPhone = text
        viewState.showErrorForPersonPhone(checkStringPhone(text))
    }

    fun checkEmail(text: String) {
        if (!(checkStringEmail(text) or checkSymbols(text, 7))) order.personEmail = text
        viewState.showErrorForPersonEmail(checkStringEmail(text) or checkSymbols(text, 7))
    }

    fun checkFormOnEmpty(): Boolean = order.personName.isNullOrEmpty() ||
                                      order.personPhone.isNullOrEmpty() ||
                                      order.personEmail.isNullOrEmpty()

    fun setPaymentType(text:String) {
        order.paymentType = text
    }

    fun setSumOrder(){
        order.initialSumOrder = cart.initialSum
        order.discount = cart.savingSum
        order.totalSumOrder = cart.totalSum
        launch {
            var number = api.createOrder(order)
            clearCartUseCase.invoke()
            order = Order(products = cart.productList)
            viewState.navigateToSuccess(number)
        }
    }
    fun getInitialPrice(): String = cart.initialSum.toString() + "P"
    fun getSavingMoney(): String = cart.savingSum.toString() + "P"
    fun getTotalPrice(): String = cart.totalSum.toString() + "P"
    private fun checkSymbols(text: String, lgth: Int): Boolean = text.length < lgth
    private fun checkStringPhone(text: String): Boolean = !(Regex("(\\+7|8)\\d{10}").matches(text))
    private fun checkStringEmail(text: String): Boolean = !("@" in text)

}