package com.dgheorghe.userpost.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dgheorghe.userpost.R
import com.dgheorghe.userpost.domain.Post
import com.dgheorghe.userpost.ui.theme.StyledColors
import com.dgheorghe.userpost.ui.theme.StyledText
import com.dgheorghe.userpost.ui.viewmodel.UserPostScreenState
import com.dgheorghe.userpost.ui.viewmodel.UserPostViewModel

object UserPostPage {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen(
        navController: NavController,
        userId: Int,
        contactAvatarString: String,
        contactName: String,
        contactEmail: String
    ) {
        val viewModel = UserPostViewModel(userId.toLong())
        val postListState by viewModel.postListState.collectAsState()

        Scaffold(topBar = { UserPostTopBar(navController) }) {
            when (postListState) {
                is UserPostScreenState.Loading -> LoadingState()
                else -> with((postListState as UserPostScreenState.Success).postList) {
                    SuccessState(
                        it,
                        contactAvatarString,
                        contactName,
                        contactEmail,
                        posts = this
                    )
                }
            }
        }
    }

    @Composable
    private fun SuccessState(
        paddingValues: PaddingValues,
        contactAvatarString: String,
        contactName: String,
        contactEmail: String,
        posts: List<Post>
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
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

            LazyColumn(modifier = Modifier.height(480.dp)) {
                itemsIndexed(posts) { _, posting ->
                    UserPostCard(posting = posting)
                }
            }
        }
    }

    @Composable
    private fun LoadingState() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = StyledColors.DARK_GRAY, strokeWidth = 2.dp)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun UserPostTopBar(navController: NavController) {
        TopAppBar(
            title = { TopBarTitle() },
            navigationIcon = { TopBarIcon(navController) },
        )
    }

    @Composable
    private fun TopBarTitle() = Text(
        text = stringResource(id = R.string.user_post_appbar_title),
        style = StyledText.displayBold
    )

    @Composable
    private fun TopBarIcon(navController: NavController) = IconButton(
        onClick = { navigateToContactList(navController) }
    ) {
        Image(
            painter = painterResource(R.drawable.ic_navigation_back),
            contentDescription = stringResource(id = R.string.navigation_back_description)
        )
    }

    @Composable
    private fun ContactName(contactName: String) {
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = contactName,
            style = StyledText.textBoldBlack,
        )
    }

    @Composable
    private fun ContactEmail(contactEmail: String) {
        Text(
            modifier = Modifier.padding(top = 10.dp, bottom = 34.dp),
            text = contactEmail,
            style = StyledText.displayRegular,
        )
    }

    @Composable
    private fun UserPostCard(posting: Post) {
        Card(
            modifier = Modifier.padding(1.dp),
            shape = RoundedCornerShape(0.dp),
            colors = CardDefaults.cardColors(containerColor = StyledColors.GRAY_INFO)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = posting.title,
                    style = StyledText.textBoldBlack,
                    modifier = Modifier.padding(
                        top = 24.dp,
                        bottom = 10.dp
                    )
                )
                Text(
                    text = posting.body,
                    style = StyledText.displayRegular,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        }
    }

    private fun navigateToContactList(navController: NavController) =
        navController.navigate("contact-list") {
            launchSingleTop = true
        }
}