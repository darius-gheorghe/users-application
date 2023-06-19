package com.dgheorghe.userpost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dgheorghe.userpost.ui.UserPostApp
import com.dgheorghe.userpost.ui.theme.StyledColors
import com.dgheorghe.userpost.ui.viewmodel.ContactListViewModel
import com.dgheorghe.userpost.ui.viewmodel.UserPostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = StyledColors.LIGHT_GRAY
            ) {
                val contactListViewModel = viewModel<ContactListViewModel>()
                val userPostViewModel = viewModel<UserPostViewModel>()
                UserPostApp(
                    contactListViewModel,
                    userPostViewModel
                )
            }
        }
    }
}