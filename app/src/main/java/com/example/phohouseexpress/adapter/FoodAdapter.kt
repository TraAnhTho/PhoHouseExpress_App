package com.example.phohouseexpress.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.phohouseexpress.R
import com.example.phohouseexpress.app_ui.customer.order.CartManager
import com.example.phohouseexpress.data.model.Food

class FoodAdapter(private val foodList: List<Food>) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFood: ImageView = itemView.findViewById(R.id.imgFood)
        val txtName: TextView = itemView.findViewById(R.id.txtFoodName)
        val txtPrice: TextView = itemView.findViewById(R.id.txtFoodPrice)
        val txtPromotion: TextView = itemView.findViewById(R.id.txtFoodPromo)
        val btnAdd: Button = itemView.findViewById(R.id.btnAdd)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.txtName.text = food.name
        holder.txtPrice.text = "${food.price}₫"
        holder.txtPromotion.text = if (food.promotion == "yes") "Khuyến mãi" else ""
        val imageName = food.imageUrl ?: "ic_pho"
        val imageRes = holder.itemView.context.resources.getIdentifier(
            imageName, "drawable", holder.itemView.context.packageName
        )

        Glide.with(holder.itemView.context)
            .load(imageRes)
            .placeholder(R.drawable.ic_pho)
            .into(holder.imgFood)

        holder.btnAdd.setOnClickListener {
            CartManager.addToCart(food)
            Toast.makeText(holder.itemView.context, "${food.name} đã được thêm vào giỏ", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int = foodList.size


}
