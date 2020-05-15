package com.example.shop.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.App
import com.example.shop.R
import com.example.shop.domain.model.Cart
import com.example.shop.domain.model.Category
import com.example.shop.domain.model.Product
import com.example.shop.presenter.CartPresenter
import com.example.shop.presenter.CartView
import com.example.shop.presenter.CategoryPresenter
import com.example.shop.ui.ProductActivity.Companion.PRODUCT_TAG
import kotlinx.android.synthetic.main.cart_layout.*
import kotlinx.android.synthetic.main.navbar_layout.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CartActivity: BaseActivity(), CartView {

    @Inject
    lateinit var cartPresenter: CartPresenter
    private val presenter by moxyPresenter { cartPresenter }
    private val cartAdapter = CartAdapter(
        {product -> presenter.removeItem(product)},
        {product -> presenter.showProductDetails(product)}
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_layout)

        cartRv.layoutManager = LinearLayoutManager(this)
        cartRv.adapter = cartAdapter
        cartPresenter.setItems()
        headerText.text = "Корзина"
        headerCartBtn.visibility = View.GONE
        if (cartPresenter.checkCartOnEmpty()) {
            cartRv.visibility = View.GONE
        }
        else{
            textEmptyCart.visibility = View.GONE
        }
        headerBackBtn.setOnClickListener{
            finish()
        }

        cartCheckout.setOnClickListener{
            if (cartPresenter.checkCartOnEmpty()) {
                Toast.makeText(this, "Добавьте товары в корзину!", Toast.LENGTH_LONG).show()
            }
            else{
                startActivity(Intent(this, CheckoutActivity::class.java))
            }
        }
    }

    override fun setItems(cart: Cart) {
        cartAdapter.setData(cart)
    }

    override fun removeItem(position: Int) {
        cartAdapter.notifyItemRemoved(position)
    }

    override fun showProductDetails(product: Product) {
        startActivity(Intent(this, ProductActivity::class.java).apply {
            putExtra(PRODUCT_TAG, product)
        })
    }
}