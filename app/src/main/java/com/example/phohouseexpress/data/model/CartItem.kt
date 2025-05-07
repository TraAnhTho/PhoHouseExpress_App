package com.example.phohouseexpress.data.model

data class CartItem(
    val food: Food,
    var quantity: Int = 1 // ✅ có default value thì không cần truyền cũng được
)
