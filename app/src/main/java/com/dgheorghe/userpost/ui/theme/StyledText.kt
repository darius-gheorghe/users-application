package com.dgheorghe.userpost.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class StyledText {
    companion object {
        val displayBold: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = StyledColors.BLACK,
            lineHeight = 30.sp,
        )

        val displayRegular: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            color = StyledColors.DARK_GRAY_SECONDARY,
            lineHeight = 30.sp,
        )

        val textRegularBlack: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            color = StyledColors.BLACK,
            lineHeight = 22.sp,
        )

        val textRegularGray: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            color = StyledColors.DARK_GRAY_SECONDARY,
            lineHeight = 22.sp,
        )

        val textBoldWhite: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            color = StyledColors.WHITE,
            lineHeight = 22.sp,
        )

        val textBoldBlack: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            color = StyledColors.BLACK,
            lineHeight = 22.sp,
        )

        val textSemiBold: TextStyle = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = StyledColors.DARK_GRAY,
            lineHeight = 18.sp,
        )
    }
}