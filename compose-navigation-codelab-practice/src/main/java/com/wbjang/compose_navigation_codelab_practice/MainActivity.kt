package com.wbjang.compose_navigation_codelab_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.wbjang.compose_navigation_codelab_practice.ui.theme.AndroidStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidStudyTheme {
                LunchTrayApp()
            }
        }
    }
}