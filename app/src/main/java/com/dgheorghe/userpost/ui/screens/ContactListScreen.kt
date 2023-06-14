package com.dgheorghe.userpost.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.dgheorghe.userpost.R
import com.dgheorghe.userpost.ui.theme.StyledColors
import com.dgheorghe.userpost.ui.theme.StyledText

@Composable
fun ContactListScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ContactListTopBar()
        ContactDetailsCard(navController = navController, 1)
        ContactDetailsCard(navController = navController, 2)
        ContactDetailsCard(navController = navController, 3)
    }
}

@Composable
fun ContactListTopBar() {
    Box(
        modifier = Modifier.padding(
            top = 48.dp,
            start = 24.dp,
            end = 24.dp,
            bottom = 16.dp
        )
    ) {
        Text(
            text = stringResource(id = R.string.contact_list_top_bar),
            style = StyledText.displayBold
        )
    }
}

@Composable
fun ContactListTitle() {

}

@Composable
fun ContactDetailsCard(navController: NavController, userId: Long) {
    Row(
        modifier = Modifier
            .padding(
                start = 24.dp,
                top = 24.dp,
                bottom = 24.dp,
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(end = 16.dp)
                .size(46.dp)
        ) {
            if (userId.toInt() % 2 == 0)
                ContactInitialsAvatar(contactInitials = "DG")
            else
                ContactImageAvatar(imagePath = "placeHolderString")
        }
        ContactNameText(contactName = "Darius Gheorghe")
        ContactNavigationIcon(contactId = 1, navController = navController)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ContactImageAvatar(imagePath: String) {
    GlideImage(
        model = imagePath,
        contentDescription = stringResource(id = R.string.user_avatar_description),
        modifier = Modifier.fillMaxSize()
    ) {
        it
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_foreground)
            .load(imagePath)
    }
}

@Composable
fun ContactInitialsAvatar(contactInitials: String) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(SolidColor(StyledColors.LIGHT_GRAY_INACTIVE))
    }
    Text(text = contactInitials, style = StyledText.textBoldWhite)
}

@Composable
fun ContactNameText(contactName: String) {
    Text(
        text = contactName,
        style = StyledText.textRegularBlack,
    )
}

@Composable
fun ContactNavigationIcon(contactId: Long, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        IconButton(
            onClick = { navigateToUserPosts(navController) },
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_nav_icon),
                contentDescription = stringResource(id = R.string.navigation_description)
            )
        }
    }
}

fun navigateToUserPosts(navController: NavController) = navController.navigate("user-post") {
    launchSingleTop = true
}