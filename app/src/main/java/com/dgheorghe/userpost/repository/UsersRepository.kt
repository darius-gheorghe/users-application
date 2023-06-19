package com.dgheorghe.userpost.repository

import android.util.Log
import com.dgheorghe.userpost.api.UserListService
import com.dgheorghe.userpost.domain.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class UsersRepository(private val _userListService: UserListService) {

    private val _coroutineScope = CoroutineScope(Job() + Dispatchers.IO)
    val observeUserList = MutableSharedFlow<List<User>>()

    init {
        _coroutineScope.launch {
            runCatching {
                _userListService.getUsersList()
            }.onSuccess {
                observeUserList.emit(it)
            }.onFailure {
                Log.e("UsersRepository === getUsersList() failure -: ", it.message!!)
                //Used dummy data as the api seems to be down from time to time
                observeUserList.emit(DummyData.userList)
                this.cancel()
            }
        }
    }
}