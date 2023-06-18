package com.dgheorghe.userpost.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgheorghe.userpost.domain.Post
import com.dgheorghe.userpost.repository.UserPostsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class UserPostViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _userPostRepository = UserPostsRepository(savedStateHandle.get<Int>("userId")?.toLong() ?: 0)
    private var _postListState: StateFlow<UserPostScreenState>

    val postListState
        get() = _postListState

    init {
        _postListState = getUserPosts().stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            UserPostScreenState.Loading
        )
    }

    private fun getUserPosts() =
        _userPostRepository.observeUserPosts.map { UserPostScreenState.Success(it) }
}

sealed interface UserPostScreenState {
    object Loading : UserPostScreenState
    data class Success(val postList: List<Post>) : UserPostScreenState
}