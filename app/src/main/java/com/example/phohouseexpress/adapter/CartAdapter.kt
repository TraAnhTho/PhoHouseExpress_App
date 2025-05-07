package com.example.phohouseexpress.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.phohouseexpress.R
import com.example.phohouseexpress.data.model.CartItem
import com.example.phohouseexpress.data.model.Food

// Adapter nhận CartItem thay vì Food
class CartAdapter(private val items: List<CartItem>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtFoodName)
        val txtPrice: TextView = itemView.findViewById(R.id.txtFoodPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = items[position]
        val food = cartItem.food
        val quantity = cartItem.quantity
        holder.txtName.text = "${food.name} x$quantity"
        holder.txtPrice.text = "${(food.price * quantity).toInt()}₫"
    }

    override fun getItemCount(): Int = items.size
}
