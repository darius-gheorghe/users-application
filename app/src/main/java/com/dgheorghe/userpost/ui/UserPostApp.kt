package com.dgheorghe.userpost.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dgheorghe.userpost.ui.screens.ContactListScreen
import com.dgheorghe.userpost.ui.screens.UserPostScreen

@Composable
fun UserPostApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "contact-list") {
        composable(route = "contact-list") {
            ContactListScreen(navController)
        }

        composable(route = "user-post") {
            UserPostScreen(navController)
        }
    }
}