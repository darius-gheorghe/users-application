package com.dgheorghe.userpost.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dgheorghe.userpost.ui.screens.ContactListScreen
import com.dgheorghe.userpost.ui.screens.UserPostScreen

@Composable
fun UserPostApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "contact-list") {
        composable(route = "contact-list") {
            ContactListScreen(navController)
        }

        composable(
            route = "user-post/{userId}/{userAvatarString}/{userName}/{userEmail}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType },
                navArgument("userAvatarString") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            UserPostScreen(
                navController,
                backStackEntry.arguments?.getInt("userId")!!,
                backStackEntry.arguments?.getString("userAvatarString")!!,
                backStackEntry.arguments?.getString("userName")!!,
                backStackEntry.arguments?.getString("userEmail")!!
            )
        }
    }
}