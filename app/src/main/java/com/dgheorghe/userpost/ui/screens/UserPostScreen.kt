package com.dgheorghe.userpost.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun UserPostScreen(navController: NavController) {

}

fun navigateToContactList(navController: NavController) = navController.navigate("contact-list") {
    launchSingleTop = true
}