package com.dgheorghe.userpost.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dgheorghe.userpost.ui.screens.ContactListPage
import com.dgheorghe.userpost.ui.screens.UserPostPage

@Composable
fun UserPostApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "contact-list") {
        composable(route = "contact-list") {
            ContactListPage.Screen(navController)
        }

        composable(
            route = "user-post/{userId}/{userAvatarString}/{userName}/{userEmail}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType },
                navArgument("userAvatarString") { type = NavType.StringType },
                navArgument("userName") {type = NavType.StringType},
                navArgument("userEmail") { type= NavType.StringType}
            )
        ) { backStackEntry ->
            UserPostPage.Screen(
                navController,
                backStackEntry.arguments?.getString("userAvatarString")!!,
                backStackEntry.arguments?.getString("userName")!!,
                backStackEntry.arguments?.getString("userEmail")!!
            )
        }
    }
}