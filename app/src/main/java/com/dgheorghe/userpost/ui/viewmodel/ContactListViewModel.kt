package com.dgheorghe.userpost.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgheorghe.userpost.domain.User
import com.dgheorghe.userpost.repository.UsersRepository
import com.dgheorghe.userpost.ui.screens.getInitials
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.random.Random

class ContactListViewModel : ViewModel() {

    private val userListRepository = UsersRepository()
    private var _uiState: StateFlow<ContactListScreenState>
    val uiState
        get() = _uiState

    init {
        _uiState = getUsersFromRepository().stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = ContactListScreenState.Loading
        )
    }

    private fun getUsersFromRepository(): Flow<ContactListScreenState> =
        userListRepository.observeUserList
            .map {
                val activeUsers = it.filter { user -> user.status == "active" }
                val avatarStringsList = getAvatarStringForUsers(activeUsers)

                ContactListScreenState.Success(activeUsers, avatarStringsList)
            }

    private fun getAvatarStringForUsers(users: List<User>): List<String> = users.map {
        if ((it.id).toInt() % 2 == 0)
            it.name.getInitials()
        else {
            Random.nextInt(from = 0, until = 500).toString() + "-flag"
        }

    }
}

sealed interface ContactListScreenState {
    object Loading : ContactListScreenState
    data class Success(
        val usersList: List<User>,
        val avatarStringsList: List<String>
    ) : ContactListScreenState
}