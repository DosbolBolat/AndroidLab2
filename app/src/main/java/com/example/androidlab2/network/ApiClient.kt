package com.example.androidlab2.network

import com.example.androidlab2.model.Celebrity
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

object ApiClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.api-ninjas.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val instance = retrofit.create(ApiService::class.java)

    interface ApiService {
        @Headers("X-Api-Key: zK1gb/behSkF+X07W9+o/A==Eak42plvdAAkBVNu")
        @GET("celebrity")
        fun getCelebrity(@Query("name") name: String): Call<List<Celebrity>>
    }

}