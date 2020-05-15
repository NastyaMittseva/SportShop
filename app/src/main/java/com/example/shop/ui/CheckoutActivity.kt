package com.example.shop.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.get
import com.example.shop.App
import com.example.shop.R
import com.example.shop.domain.model.Order
import com.example.shop.presenter.CheckoutPresenter
import com.example.shop.presenter.CheckoutView
import com.example.shop.ui.SuccessCheckoutActivity.Companion.NUMBER_ORDER_TAG
import kotlinx.android.synthetic.main.checkout_layout.*
import kotlinx.android.synthetic.main.navbar_layout.*
import javax.inject.Inject

class CheckoutActivity() : BaseActivity(),
    CheckoutView {

    @Inject
    lateinit var presenter: CheckoutPresenter

    private var isAuth:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.checkout_layout)
        presenter.attachView(this)
        setListeners()

        headerText.text = "Оформление заказа"
        initialSum.text = presenter.getInitialPrice()
        savingMoney.text = presenter.getSavingMoney()
        totalSum.text = presenter.getTotalPrice()

        checkoutOrderBtn.setOnClickListener(){
            if (presenter.checkFormOnEmpty()) {
                Toast.makeText(this, "Заполните все поля формы!", Toast.LENGTH_LONG).show()
            }
            else{
                val paymentType: RadioButton = findViewById(paymentMethod.checkedRadioButtonId)
                presenter.setPaymentType(paymentType.text.toString())
                presenter.setSumOrder()
            }
        }

        headerBackBtn.setOnClickListener(){
            finish()
        }

        headerCartBtn.setOnClickListener(){
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    private fun setListeners(){
        personName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkPersonName(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        personPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkPhone(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        personEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkEmail(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    fun EditText.showError(visible:Boolean){
        val drawable = if (visible) R.drawable.ic_error
        else 0
        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0)
    }

    override fun showErrorForPersonName(visible: Boolean) {
        personName.showError(visible)
    }

    override fun showErrorForPersonPhone(visible: Boolean) {
        personPhone.showError(visible)
    }

    override fun showErrorForPersonEmail(visible: Boolean) {
        personEmail.showError(visible)
    }

    override fun navigateToSuccess(orderNumber: Long) {
        startActivity(Intent(this, SuccessCheckoutActivity::class.java).apply {
            putExtra(NUMBER_ORDER_TAG, orderNumber)
        })
    }
}