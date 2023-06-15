package com.dgheorghe.userpost.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgheorghe.userpost.domain.Post
import com.dgheorghe.userpost.repository.UserPostsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class UserPostViewModel(userId: Long) : ViewModel() {
    private val _userPostRepository = UserPostsRepository(userId)
    private var _postListState: StateFlow<List<Post>> = _userPostRepository.observeUserPosts
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            emptyList()
        )

    val postListState
        get() = _postListState
}