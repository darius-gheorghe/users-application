package com.dgheorghe.userpost.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgheorghe.userpost.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ContactListViewModel : ViewModel() {

    private var _uiState: StateFlow<ContactListScreenState>
    val uiState
        get() = _uiState

    init {
        _uiState = getUsersFromRepository().stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = ContactListScreenState(emptyList())
        )
    }

    private fun getUsersFromRepository(): Flow<ContactListScreenState> = flowOf(DummyData.userList)
        .map {
            ContactListScreenState(
                it.filter { user -> user.status == "active" }
            )
        }
}

data class ContactListScreenState(val usersList: List<User>)