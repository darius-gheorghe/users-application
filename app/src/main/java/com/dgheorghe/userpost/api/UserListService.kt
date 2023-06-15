package com.dgheorghe.userpost.api

import com.dgheorghe.userpost.domain.User
import retrofit2.http.GET

interface UserListService {

    @GET("users")
    suspend fun getUsersList(): List<User>
}