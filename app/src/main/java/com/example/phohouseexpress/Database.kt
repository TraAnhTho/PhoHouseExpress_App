package com.example.phohouseexpress
import com.example.phohouseexpress.data.model.Food
import java.sql.DriverManager

// Database.kt
    fun getAllFoodsFromDatabase(): List<Food> {
        val connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "root", "")
        val statement = connection.prepareStatement("SELECT * FROM food")
        val resultSet = statement.executeQuery()

        val foods = mutableListOf<Food>()
        while (resultSet.next()) {
            foods.add(
                Food(
                    id = resultSet.getInt("id"),
                    name = resultSet.getString("name"),
                    price = resultSet.getDouble("price"),
                    promotion = resultSet.getString("promotion"),
                    imageUrl = resultSet.getString("image_url")
                )
            )
        }
        connection.close()
        return foods
    }
