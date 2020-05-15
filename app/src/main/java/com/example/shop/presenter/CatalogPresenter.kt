package com.example.shop.presenter

import com.example.shop.domain.MainApi
import com.example.shop.domain.model.Category
import kotlinx.coroutines.launch
import moxy.InjectViewState
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject


@InjectViewState
class CatalogPresenter @Inject constructor(
    private val mainApi: MainApi
    ): BasePresenter<CatalogView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launch {
            val remoteCategories = mainApi.allCategories()
            val categories = remoteCategories.map { remoteCategory -> Category(remoteCategory.id, remoteCategory.name) }
            viewState.setCategory(categories)
        }
    }

    override fun onFailure(e: Throwable) {
        super.onFailure(e)
        if (e is ConnectException){
            viewState.showError("Ошибка сети, подключитесь к интернету")
        }
        if (e is UnknownHostException){
            viewState.showError("Ошибка соединения с сервером")
        }
    }

    fun showProductsInCategory(category: Category) {
        viewState.showProductsInCategory(category)
    }
}
