package com.example.shop.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.App
import com.example.shop.R
import com.example.shop.domain.model.Category
import com.example.shop.presenter.CatalogPresenter
import com.example.shop.presenter.CatalogView
import com.example.shop.ui.CategoryActivity.Companion.CATEGORY_TAG
import kotlinx.android.synthetic.main.catalog_layout.*
import kotlinx.android.synthetic.main.navbar_layout.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CatalogActivity : BaseActivity(), CatalogView {

    @Inject
    lateinit var catalogPresenter: CatalogPresenter

    private val presenter by moxyPresenter { catalogPresenter }
    private val catalogAdapter = CatalogAdapter { category -> presenter.showProductsInCategory(category)}

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.catalog_layout)
        categoryRv.layoutManager = LinearLayoutManager(this)
        categoryRv.adapter = catalogAdapter
        headerText.text = "Каталог"
        headerBackBtn.visibility = View.GONE
    }

    override fun setCategory(categories: List<Category>) {
        catalogAdapter.setData(categories)
    }

    override fun showProductsInCategory(category: Category) {
        startActivity(Intent(this, CategoryActivity::class.java).apply {
            putExtra(CATEGORY_TAG, category)
        })
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}
