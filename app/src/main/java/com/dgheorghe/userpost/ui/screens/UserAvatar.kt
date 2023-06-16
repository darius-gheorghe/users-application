package com.dgheorghe.userpost.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dgheorghe.userpost.ui.theme.StyledColors
import com.dgheorghe.userpost.ui.theme.StyledText

object UserAvatar {
    @Composable
    fun GetAvatarForUser(avatarString: String) = if (avatarString.length <= 2)
        ContactInitialsAvatar(contactInitials = avatarString)
    else
        ContactImageAvatar(imageId = avatarString)

    @Composable
    private fun ContactImageAvatar(imageId: String) {
        val imageIdFlagRemoved = imageId.split("-flag")[0]
        Image(
            painter = rememberAsyncImagePainter("https://picsum.photos/id/${imageIdFlagRemoved}/200/200"),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(50.dp))
        )
    }

    @Composable
    private fun ContactInitialsAvatar(contactInitials: String) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(SolidColor(StyledColors.LIGHT_GRAY_INACTIVE))
        }
        Text(text = contactInitials, style = StyledText.textBoldWhite)
    }
}

fun String.getInitials(): String = this.split(" ").let {
    "${it[0][0]}${(it[1][0])}"
}