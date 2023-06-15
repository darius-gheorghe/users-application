package com.dgheorghe.userpost.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUserInstance {
    private const val BASE_URL: String = "https://gorest.co.in/public/v2/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val userListService by lazy {
        retrofit.create(UserListService::class.java)
    }

    val userPostService by lazy {
        retrofit.create(UserPostService::class.java)
    }
}