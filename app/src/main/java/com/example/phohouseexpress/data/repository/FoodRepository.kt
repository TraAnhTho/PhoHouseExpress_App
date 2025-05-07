package com.example.phohouseexpress.repository

import com.example.phohouseexpress.utils.ApiService
import com.example.phohouseexpress.utils.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class FoodRepository {
    private val apiService = RetrofitClient.instance.create(ApiService::class.java)

    suspend fun addFood(name: String, price: Double, promotion: String?, imageFile: File) {
        val nameBody = RequestBody.create("text/plain".toMediaTypeOrNull(), name)
        val priceBody = RequestBody.create("text/plain".toMediaTypeOrNull(), price.toString())
        val promoBody = RequestBody.create("text/plain".toMediaTypeOrNull(), promotion ?: "")

        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), imageFile)
        val imagePart = MultipartBody.Part.createFormData("image", imageFile.name, requestFile)

        apiService.addFood(nameBody, priceBody, promoBody, imagePart)
    }
}
