package com.example.shop.presenter

import com.example.shop.domain.MainApi
import com.example.shop.domain.CartDao
import com.example.shop.domain.interactor.DeleteProductFromCartUseCase
import com.example.shop.domain.model.Cart
import com.example.shop.domain.model.Product
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartPresenter @Inject constructor(
    private val cart: Cart,
    private val mainApi: MainApi,
    private val cartDao: CartDao,
    private val deleteProductFromCartUseCase: DeleteProductFromCartUseCase
    ): BasePresenter<CartView>() {

    fun setItems(){
        launch {
            val productIds = cartDao.getAllProducts()
            val remoteProducts = mainApi.allProducts()
                .filter { it.id in productIds }
                .map { it -> Product(it.id, it.name, it.price, it.discountPercent, it.description,
                    it.imageUrl, it.category) }
            cart.updateProducts(remoteProducts)
            viewState.setItems(cart)
        }
    }

    fun removeItem(product: Product){
        val position = cart.productList.indexOf(product)
        deleteProductFromCartUseCase.invoke(product)
        viewState.removeItem(position)
    }

    fun showProductDetails(product: Product) {
        viewState.showProductDetails(product)
    }

    fun checkCartOnEmpty(): Boolean = cart.getCountProducts() == 0

}