package com.example.shop.presenter

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import moxy.MvpPresenter
import moxy.MvpView
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<TView : MvpView> : MvpPresenter<TView>(), CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext =
        Dispatchers.Main + job + CoroutineExceptionHandler { _, e ->
            onFailure(e)
        }

    open fun onFailure(e: Throwable) {}

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}