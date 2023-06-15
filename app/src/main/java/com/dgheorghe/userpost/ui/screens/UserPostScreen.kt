package com.dgheorghe.userpost.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.dgheorghe.userpost.R
import com.dgheorghe.userpost.ui.theme.StyledText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserPostScreen(
    navController: NavController,
    userId: Int,
    contactAvatarString: String,
    contactName: String,
    contactEmail: String
) {
    Scaffold(topBar = { UserPostTopBar(navController) }) {
        Column(modifier = Modifier.padding(it)) {
            Text(text = "$userId" + contactAvatarString + contactName + contactEmail)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserPostTopBar(navController: NavController) {
    TopAppBar(
        title = { TopBarTitle() },
        navigationIcon = { TopBarIcon(navController) },
    )
}

@Composable
fun TopBarTitle() = Text(
    text = stringResource(id = R.string.user_post_appbar_title),
    style = StyledText.displayBold
)

@Composable
fun TopBarIcon(navController: NavController) = IconButton(
    onClick = { navigateToContactList(navController) }
) {
    Image(
        painter = painterResource(R.drawable.ic_navigation_back),
        contentDescription = stringResource(id = R.string.navigation_back_description)
    )
}

fun navigateToContactList(navController: NavController) = navController.navigate("contact-list") {
    launchSingleTop = true
}