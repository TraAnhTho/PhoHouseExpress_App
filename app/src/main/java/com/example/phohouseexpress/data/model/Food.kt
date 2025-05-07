package com.example.phohouseexpress.data.model

data class Food(
    val id: Int?,
    val name: String,
    val price: Double,
    val promotion: String?, // null nếu không có khuyến mãi
    val imageUrl: String
)
