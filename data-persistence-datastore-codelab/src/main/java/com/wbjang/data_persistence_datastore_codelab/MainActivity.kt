package com.wbjang.data_persistence_datastore_codelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.wbjang.data_persistence_datastore_codelab.ui.DessertReleaseApp
import com.wbjang.data_persistence_datastore_codelab.ui.theme.AndroidStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidStudyTheme() {
                DessertReleaseApp()
            }
        }
    }
}
