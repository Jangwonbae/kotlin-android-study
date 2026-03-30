package com.wbjang.views_and_compose_codelab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.wbjang.views_and_compose_codelab2.ui.JuiceTrackerApp
import com.wbjang.views_and_compose_codelab2.ui.theme.JuiceTrackerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            JuiceTrackerTheme {
                JuiceTrackerApp()
            }
        }
    }
}