package com.example.shop.domain.interactor

import com.example.shop.domain.CartDao
import com.example.shop.domain.model.Cart
import com.example.shop.domain.model.Product
import javax.inject.Inject

class DeleteProductFromCartUseCase @Inject constructor(
    private val cart: Cart,
    private val cartDao: CartDao
) {
    operator fun invoke(product: Product){
        cart.deleteProduct(product)
        cartDao.deleteProduct(product.id)
    }
}