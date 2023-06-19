package com.dgheorghe.userpost.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgheorghe.userpost.domain.Post
import com.dgheorghe.userpost.repository.UserPostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserPostViewModel @Inject constructor(
    private val _userPostRepository: UserPostRepository,
) : ViewModel() {

    private var _postListState: StateFlow<UserPostScreenState> = MutableStateFlow(UserPostScreenState.Loading)
    val postListState
        get() = _postListState

    fun updateUserPosts(userId: Long) {
        _postListState = getUserPosts(userId).stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            UserPostScreenState.Success(emptyList())
        )
    }

    fun clearUserPostsFlow() {
        _postListState = MutableStateFlow(UserPostScreenState.Loading)
    }

    private fun getUserPosts(userId: Long) =
        _userPostRepository.getUserPosts(userId).map { UserPostScreenState.Success(it) }
}

sealed interface UserPostScreenState {
    object Loading : UserPostScreenState
    data class Success(val postList: List<Post>) : UserPostScreenState
}