package com.dgheorghe.userpost.repository

import com.dgheorghe.userpost.api.RetrofitUserInstance
import com.dgheorghe.userpost.domain.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class UserPostsRepository(userId: Long) {
    private val userPostService = RetrofitUserInstance.userPostService
    private val _coroutineScope = CoroutineScope(Job() + Dispatchers.IO)

    val observeUserPosts = MutableSharedFlow<List<Post>>()

    init {
        _coroutineScope.launch {
            runCatching {
                userPostService.getUserPosts(userId)
            }.onSuccess {
                observeUserPosts.tryEmit(it)
            }.onFailure {
                observeUserPosts.tryEmit(DummyData.getDummyPostList(userId))
                this.cancel()
            }
        }
    }
}