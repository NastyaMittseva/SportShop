package com.example.shop.ui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.domain.model.Product
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_product.*
import kotlin.math.roundToInt


class CategoryAdapter(
    private val onProductClick: (category: Product) -> Unit
): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var products: List<Product> = listOf()

    fun setData(products: List<Product>){
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(product: Product){
            productIv.text = product.name
            priceIv.setPaintFlags(priceIv.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
            priceIv.text = product.price.roundToInt().toString() + " P"
            newPriceIv.text = product.calcDiscountPrice().roundToInt().toString() + " P"

            containerView.setOnClickListener {
                onProductClick(product)
            }
        }
    }
}