package com.dgheorghe.userpost.ui.screens

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.dgheorghe.userpost.ui.theme.StyledText
import com.dgheorghe.userpost.ui.theme.UserPostTheme

@Composable
fun ContactListScreen(navController: NavController) {
    TextButton(onClick = { navigateToUserPosts(navController) }) {
        Greeting("Android")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        style = StyledText.displayBold
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UserPostTheme {
        Greeting("Android")
    }
}

fun navigateToUserPosts(navController: NavController) = navController.navigate("user-post") {
    launchSingleTop = true
}