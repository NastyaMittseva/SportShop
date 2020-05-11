package com.example.shop.presenter

import com.example.shop.domain.MainApi
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
            val remoteCategories = mainApi.allCategories("default")
            val categoriesNames = remoteCategories.map { remoteCategory -> remoteCategory.name }
            viewState.setCategoryNames(categoriesNames)
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
}