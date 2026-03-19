package com.wbjang.data_persistence_room_codelab_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.wbjang.data_persistence_room_codelab_practice.ui.BusScheduleApp
import com.wbjang.data_persistence_room_codelab_practice.ui.theme.AndroidStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidStudyTheme() {
                BusScheduleApp()
            }
        }
    }
}