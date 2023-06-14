package com.dgheorghe.userpost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.dgheorghe.userpost.ui.UserPostApp
import com.dgheorghe.userpost.ui.theme.StyledColors
import com.dgheorghe.userpost.ui.theme.UserPostTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserPostTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = StyledColors.WHITE
                ) {
                    UserPostApp()
                }
            }
        }
    }
}