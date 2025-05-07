package com.example.phohouseexpress.utils

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object ApiClient {
    private const val BASE_URL = "http://192.168.1.112/phohouseapi/" // chỉnh lại theo IP/host của bạn

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
