package com.dgheorghe.userpost.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgheorghe.userpost.domain.User
import com.dgheorghe.userpost.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

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

    private fun getUsersFromRepository(): Flow<ContactListScreenState> = userListRepository.observeUserList
        .map {
            ContactListScreenState.Success(
                it.filter { user -> user.status == "active" }
            )
        }
}

sealed interface ContactListScreenState {
    object Loading : ContactListScreenState
    data class Success(val usersList: List<User>): ContactListScreenState
}