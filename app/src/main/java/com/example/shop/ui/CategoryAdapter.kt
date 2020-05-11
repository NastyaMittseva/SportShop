package com.example.shop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_category.*

class CategoryAdapter(
): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var categories: List<String> = listOf()

    fun setData(categories: List<String>){
        this.categories = categories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(text: String){
            categoryIv.text = text
        }
    }
}