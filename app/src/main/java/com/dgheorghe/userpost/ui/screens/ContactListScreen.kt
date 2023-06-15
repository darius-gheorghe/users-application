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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.dgheorghe.userpost.R
import com.dgheorghe.userpost.domain.User
import com.dgheorghe.userpost.ui.theme.StyledColors
import com.dgheorghe.userpost.ui.theme.StyledText
import com.dgheorghe.userpost.ui.viewmodel.ContactListViewModel

@Composable
fun ContactListScreen(navController: NavController, viewModel: ContactListViewModel = viewModel()) {
    val userList by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        ContactListTopBar()
        ContactListTitle()
        LazyColumn(
            modifier = Modifier.height(564.dp)
        ) {
            itemsIndexed(userList.usersList) { _, userDetails ->
                ContactDetailsCard(navController = navController, userDetails)
            }
        }
    }
}

@Composable
fun ContactListTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(StyledColors.WHITE)
            .padding(
                top = 48.dp,
                start = 24.dp,
                end = 24.dp,
                bottom = 16.dp
            )
    ) {
        Text(
            text = stringResource(id = R.string.contact_list_top_bar),
            style = StyledText.displayBold,
        )
    }
}

@Composable
fun ContactListTitle() {
    Text(
        text = stringResource(id = R.string.contact_list_title),
        style = StyledText.textSemiBold,
        modifier = Modifier.padding(
            vertical = 12.dp,
            horizontal = 24.dp
        )
    )
}

@Composable
fun ContactDetailsCard(navController: NavController, userDetails: User) {
    Card(
        modifier = Modifier.padding(1.dp),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = StyledColors.WHITE)
    ) {
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
                    if (userDetails.id.toInt() % 2 == 0)
                        ContactInitialsAvatar(contactInitials = userDetails.name.getInitials())
                    else
                        ContactImageAvatar(imagePath = "placeHolderString")
                }
                ContactNameText(contactName = userDetails.name)
            ContactNavigationIcon(contactId = 1, navController = navController)
        }
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

fun String.getInitials(): String = this.split(" ").let {
    "${it[0][0]}${(it[1][0])}"
}

fun navigateToUserPosts(navController: NavController) = navController.navigate("user-post") {
    launchSingleTop = true
}