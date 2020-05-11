package com.example.shop.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.App
import com.example.shop.R
import com.example.shop.domain.model.Category
import com.example.shop.domain.model.Product
import com.example.shop.presenter.CategoryPresenter
import com.example.shop.presenter.CategoryView
import kotlinx.android.synthetic.main.catalog_layout.*
import kotlinx.android.synthetic.main.category_layout.*
import kotlinx.android.synthetic.main.navbar_layout.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CategoryActivity: BaseActivity(), CategoryView {

    @Inject
    lateinit var categoryPresenter: CategoryPresenter
    private val presenter by moxyPresenter { categoryPresenter }
    private val categoryAdapter = CategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_layout)
        val category = intent?.getParcelableExtra<Category>(CATEGORY_TAG) ?: return

        productRv.layoutManager = LinearLayoutManager(this)
        productRv.adapter = categoryAdapter

        headerText.text = category.name
        presenter.onProductShow(category.id)

        headerBackBtn.setOnClickListener{
            startActivity(Intent(this, CatalogActivity::class.java))
        }
    }

    companion object {
        const val CATEGORY_TAG = "CATEGORY_TAG"
    }

    override fun setProducts(list: List<Product>) {
        categoryAdapter.setData(list)
    }

}