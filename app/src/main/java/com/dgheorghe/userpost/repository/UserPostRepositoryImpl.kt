package com.dgheorghe.userpost.repository

import android.util.Log
import com.dgheorghe.userpost.api.UserPostService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class UserPostRepositoryImpl(
    private val _userPostService: UserPostService
) : UserPostRepository {

    private val _coroutineScope = CoroutineScope(Job() + Dispatchers.IO)

    private suspend fun observeUserPosts(userId: Long) =
        withContext(_coroutineScope.coroutineContext) {
            runCatching {
                _userPostService.getUserPosts(userId)
            }.onFailure {
                Log.e("UserPostsRepository === getUserPosts() failure -: ", it.message!!)
                this.cancel()
            }
        }

    override fun getUserPosts(userId: Long) = flow {
        observeUserPosts(userId)
            .onSuccess {
                emit(it)
            }.onFailure {
                //Used dummy data as the api seems to be down from time to time
                emit(DummyData.getDummyPostList())
            }
    }
}