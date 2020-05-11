package com.example.shop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.domain.model.Category
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_category.*

class CatalogAdapter(
    private val onCategoryClick: (category: Category) -> Unit
): RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {

    private var categories: List<Category> = listOf()

    fun setData(categories: List<Category>){
        this.categories = categories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogAdapter.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(category: Category){
            categoryIv.text = category.name
            containerView.setOnClickListener {
                onCategoryClick(category)
            }
        }
    }
}