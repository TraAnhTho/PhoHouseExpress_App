package com.example.phohouseexpress.app_ui.customer.order

import com.example.phohouseexpress.data.model.CartItem
import com.example.phohouseexpress.data.model.Food

object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun addToCart(food: Food) {
        val existing = cartItems.find { it.food.id == food.id }
        if (existing != null) existing.quantity++
        else cartItems.add(CartItem(food))
    }

    fun removeFromCart(food: Food) {
        cartItems.removeIf { it.food.id == food.id }
    }

    fun getCartItems(): List<CartItem> = cartItems

    fun getTotalPrice(): Double = cartItems.sumOf { it.food.price * it.quantity }

    fun clearCart() {
        cartItems.clear()
    }
}
