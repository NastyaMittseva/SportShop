package com.example.shop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.domain.model.Cart
import com.example.shop.domain.model.Product
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_cart.*
import kotlin.math.roundToInt

class CartAdapter(
    private val onDeleteClick: (product: Product) -> Unit,
    private val onProductClick: (product: Product) -> Unit
): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private var products = listOf<Product>()

    fun setData(cart: Cart){
        this.products = cart.productList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false))

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(product: Product) {
            cartProductName.text = product.name
            cartProductPrice.text = product.calcDiscountPrice().roundToInt().toString()

            cartDeleteProduct.setOnClickListener{
                onDeleteClick(product)
            }

            containerView.setOnClickListener {
                onProductClick(product)
            }
        }
    }
}