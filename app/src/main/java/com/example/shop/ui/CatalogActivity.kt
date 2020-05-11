package com.example.shop.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.App
import com.example.shop.R
import com.example.shop.presenter.CatalogPresenter
import com.example.shop.presenter.CatalogView
import kotlinx.android.synthetic.main.catalog_layout.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CatalogActivity : BaseActivity(), CatalogView {

    @Inject
    lateinit var catalogPresenter: CatalogPresenter

    private val presenter by moxyPresenter { catalogPresenter }
    private val categoryAdapter = CategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.catalog_layout)
        categoryRv.layoutManager = LinearLayoutManager(this)
        categoryRv.adapter = categoryAdapter
    }

    override fun setCategoryNames(list: List<String>) {
        categoryAdapter.setData(list)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}
