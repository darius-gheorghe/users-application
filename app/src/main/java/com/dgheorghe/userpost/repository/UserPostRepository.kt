package com.dgheorghe.userpost.repository

import com.dgheorghe.userpost.domain.Post
import kotlinx.coroutines.flow.Flow

interface UserPostRepository {
    fun getUserPosts(userId: Long): Flow<List<Post>>
}