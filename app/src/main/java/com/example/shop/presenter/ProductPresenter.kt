package com.example.shop.presenter

import com.example.shop.domain.interactor.AddProductToCartUseCase
import com.example.shop.domain.model.Product
import moxy.MvpPresenter
import javax.inject.Inject

class ProductPresenter @Inject constructor(
    private val useCase: AddProductToCartUseCase
): MvpPresenter<ProductView>()  {
    fun addProductToCart(product: Product){
        useCase.invoke(product)
    }
}