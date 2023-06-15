package com.dgheorghe.userpost.api

import com.dgheorghe.userpost.domain.Post
import retrofit2.http.GET
import retrofit2.http.QueryName

interface UserPostService {
    @GET("users")
    fun queryUserPosts(@QueryName query: String): List<Post>

    fun getUserPosts(userId: Long) = queryUserPosts("${userId}/posts")
}