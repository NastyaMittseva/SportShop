package com.example.shop.presenter

import com.example.shop.domain.ViewedProductDao
import com.example.shop.domain.model.Product
import moxy.MvpPresenter
import javax.inject.Inject

class ProductPresenter @Inject constructor(
    private val viewedProductDao: ViewedProductDao
):
    MvpPresenter<ProductView>()  {
    fun onProductShow(product: Product) {
        viewedProductDao.addProduct(product.id)
    }
}