package com.wbjang.coroutines_rest_codelab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.wbjang.coroutines_rest_codelab2.ui.MarsPhotosApp
import com.wbjang.coroutines_rest_codelab2.ui.theme.AndroidStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AndroidStudyTheme {
                MarsPhotosApp()
            }
        }
    }
}
