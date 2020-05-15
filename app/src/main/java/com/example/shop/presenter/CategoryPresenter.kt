package com.example.shop.presenter

import android.util.Log
import com.example.shop.domain.MainApi
import com.example.shop.domain.model.Product
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class CategoryPresenter @Inject constructor(
    private val mainApi: MainApi
): BasePresenter<CategoryView>()  {

    fun onProductShow(categoryId: String) {
        launch {
            val remoteProducts = mainApi.allProducts()
                .filter { it.category == categoryId }
                .map { it -> Product(it.id, it.name, it.price, it.discountPercent, it.description,
                    it.imageUrl, it.category) }
            viewState.setProducts(remoteProducts)
        }
    }

    fun showProductInfo(product: Product){
        viewState.showProductInfo(product)
    }
}