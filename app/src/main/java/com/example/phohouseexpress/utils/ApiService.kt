package com.example.phohouseexpress.utils


import com.example.phohouseexpress.data.model.Food
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("login.php")
    fun login(@Field("username") username: String, @Field("password") password: String): Call<ResponseBody>

    @GET("get_foods.php")
    suspend fun getFoods(): List<Food>


    @Multipart
    @POST("add_food.php")
    suspend fun addFood(
        @Part("name") name: RequestBody,
        @Part("price") price: RequestBody,
        @Part("promotion") promotion: RequestBody,
        @Part image: MultipartBody.Part
    ): Response<ResponseBody>


    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseBody>

}

