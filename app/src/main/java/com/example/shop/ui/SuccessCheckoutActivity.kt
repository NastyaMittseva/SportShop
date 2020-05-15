package com.example.shop.ui

import android.content.Intent
import android.os.Bundle
import com.example.shop.App
import com.example.shop.R
import com.example.shop.presenter.SuccessCheckoutView
import kotlinx.android.synthetic.main.navbar_success_layout.*
import kotlinx.android.synthetic.main.success_checkout_layout.*

class SuccessCheckoutActivity: BaseActivity(),
    SuccessCheckoutView {
    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.success_checkout_layout)
        val orderNumber = intent?.getLongExtra(NUMBER_ORDER_TAG, 0) ?: return
        successCheckoutText.text = "Заказ № ${orderNumber} успешно оформлен!"

        headerCatalogBtn.setOnClickListener{
            startActivity(Intent(this, CatalogActivity::class.java))
        }
    }

    companion object {
        const val NUMBER_ORDER_TAG = "NUMBER_ORDER_TAG"
    }
}