package com.example.phohouseexpress.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phohouseexpress.repository.FoodRepository
import kotlinx.coroutines.launch
import java.io.File

class AdminViewModel : ViewModel() {
    private val repository = FoodRepository()

    fun addFood(name: String, price: Double, promotion: String?, imageFile: File) {
        viewModelScope.launch {
            repository.addFood(name, price, promotion, imageFile)
        }
    }
}
