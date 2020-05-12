package com.example.shop.ui

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.shop.App
import com.example.shop.R
import com.example.shop.domain.model.Product
import com.example.shop.presenter.ProductPresenter
import com.example.shop.presenter.ProductView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.navbar_layout.*
import kotlinx.android.synthetic.main.product_layout.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import kotlin.math.roundToInt

class ProductActivity: BaseActivity(), ProductView {

    @Inject
    lateinit var productPresenter: ProductPresenter

    private val presenter by moxyPresenter { productPresenter }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_layout)
        val product = intent?.getParcelableExtra<Product>(PRODUCT_TAG) ?: return
        productTitle.text = product.name
        productPrice.setPaintFlags(productPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
        productPrice.text = product.price.toString()
        newProductPrice.text = product.calcDiscountPrice().roundToInt().toString()
        productSaving.text = product.calcSavingMoney().roundToInt().toString()
        productDescription.text = product.description
        Picasso.get().load(product.imageUrl).into(productImage)
        presenter.onProductShow(product)

        headerBackBtn.setOnClickListener{
            finish()
        }

        productBasketButton.setOnClickListener{
            presenter.addProductToCart(product)
            Toast.makeText(this, "Товар добавлен в корзину", Toast.LENGTH_LONG).show()
        }

        cartBtn.setOnClickListener{
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    companion object {
        const val PRODUCT_TAG = "PRODUCT_TAG"
    }

}