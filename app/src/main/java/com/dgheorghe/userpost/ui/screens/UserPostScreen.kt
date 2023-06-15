package com.dgheorghe.userpost.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(46.dp)
            ) {
                GetAvatarForUser(avatarString = contactAvatarString)
            }
            ContactName(contactName = contactName)
            ContactEmail(contactEmail = contactEmail)
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

@Composable
fun ContactName(contactName: String) {
    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = contactName,
        style = StyledText.textBoldBlack,
    )
}

@Composable
fun ContactEmail(contactEmail: String) {
    Text(
        modifier = Modifier.padding(top = 10.dp, bottom = 34.dp),
        text = contactEmail,
        style = StyledText.displayRegular,
    )
}

fun navigateToContactList(navController: NavController) = navController.navigate("contact-list") {
    launchSingleTop = true
}