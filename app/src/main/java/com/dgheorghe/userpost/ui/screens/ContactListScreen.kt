package com.dgheorghe.userpost.ui.screens

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dgheorghe.userpost.R
import com.dgheorghe.userpost.domain.User
import com.dgheorghe.userpost.ui.theme.StyledColors
import com.dgheorghe.userpost.ui.theme.StyledText
import com.dgheorghe.userpost.ui.viewmodel.ContactListScreenState
import com.dgheorghe.userpost.ui.viewmodel.ContactListViewModel

object ContactListPage {
    @Composable
    fun Screen(
        navController: NavController,
        viewModel: ContactListViewModel = viewModel()
    ) {
        val uiState by viewModel.uiState.collectAsState()

        when (uiState) {
            is ContactListScreenState.Success -> SuccessState(
                userList = (uiState as ContactListScreenState.Success).usersList,
                navController = navController
            )

            is ContactListScreenState.Loading -> LoadingState()
        }
    }

    @Composable
    private fun LoadingState() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = StyledColors.WHITE, strokeWidth = 5.dp)
        }
    }

    @Composable
    private fun SuccessState(userList: List<User>, navController: NavController) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(1.dp),
        ) {
            ContactListTopBar()
            ContactListTitle()
            LazyColumn(
                modifier = Modifier.height(564.dp)
            ) {
                itemsIndexed(userList) { _, userDetails ->
                    ContactDetailsCard(navController = navController, userDetails)
                }
            }
        }
    }

    @Composable
    private fun ContactListTopBar() {
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
    private fun ContactListTitle() {
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
    private fun ContactDetailsCard(navController: NavController, userDetails: User) {
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
                val avatarString = getUserAvatarString(userDetails.id.toInt(), userDetails.name)

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(46.dp)
                ) {
                    GetAvatarForUser(avatarString = avatarString)
                }
                ContactNameText(contactName = userDetails.name)
                ContactNavigationIcon(
                    contactId = userDetails.id,
                    navController = navController,
                    contactAvatarString = avatarString,
                    contactName = userDetails.name,
                    contactEmail = userDetails.email
                )
            }
        }
    }

    @Composable
    private fun ContactNameText(contactName: String) {
        Text(
            text = contactName,
            style = StyledText.textRegularBlack,
        )
    }

    @Composable
    private fun ContactNavigationIcon(
        contactId: Long,
        navController: NavController,
        contactAvatarString: String,
        contactName: String,
        contactEmail: String
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            IconButton(
                onClick = {
                    navigateToUserPosts(
                        navController,
                        contactId.toInt(),
                        contactAvatarString,
                        contactName,
                        contactEmail
                    )
                },
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_nav_icon),
                    contentDescription = stringResource(id = R.string.navigation_description)
                )
            }
        }
    }

    private fun navigateToUserPosts(
        navController: NavController,
        userId: Int,
        userAvatarString: String,
        userName: String,
        userEmail: String
    ) =
        navController.navigate("user-post/${userId}/${userAvatarString}/${userName}/${userEmail}") {
            launchSingleTop = true
        }
}