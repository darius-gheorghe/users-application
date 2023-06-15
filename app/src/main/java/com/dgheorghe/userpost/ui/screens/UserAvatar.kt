package com.dgheorghe.userpost.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.dgheorghe.userpost.R
import com.dgheorghe.userpost.ui.theme.StyledColors
import com.dgheorghe.userpost.ui.theme.StyledText

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
fun GetAvatarForUser(avatarString: String) = if (avatarString.length <= 2)
    ContactInitialsAvatar(contactInitials = avatarString)
else
    ContactImageAvatar(imagePath = "placeHolderString")

fun getUserAvatarString(userId: Int, userName: String): String =
    if (userId % 2 == 0) userName.getInitials()
    else "placeHolderString"

fun String.getInitials(): String = this.split(" ").let {
    "${it[0][0]}${(it[1][0])}"
}