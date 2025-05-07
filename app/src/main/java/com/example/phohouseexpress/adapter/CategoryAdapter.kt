package com.example.phohouseexpress.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.phohouseexpress.R

class CategoryAdapter(private val categories: List<String>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtCategoryName)
        val imgCategory: ImageView = itemView.findViewById(R.id.imgCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val name = categories[position]
        holder.txtName.text = name

        // Gán icon tùy theo tên
        val iconRes = when (name) {
            "Pho" -> R.drawable.ic_pho
            "Pizza" -> R.drawable.ic_hotdog
            "Burger" -> R.drawable.ic_user
            else -> R.drawable.ic_profile
        }
        holder.imgCategory.setImageResource(iconRes)
    }

    override fun getItemCount(): Int = categories.size
}

