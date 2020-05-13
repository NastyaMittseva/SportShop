package com.example.shop.domain.interactor

import com.example.shop.domain.ViewedProductDao
import com.example.shop.domain.model.Cart
import com.example.shop.domain.model.Product
import kotlinx.coroutines.delay
import javax.inject.Inject

class AddProductToCartUseCase @Inject constructor(
    private val cart: Cart,
    private val viewedProductDao: ViewedProductDao
) {
    operator fun invoke(product: Product){
        cart.addProduct(product)
        viewedProductDao.addProduct(product.id)
    }
}