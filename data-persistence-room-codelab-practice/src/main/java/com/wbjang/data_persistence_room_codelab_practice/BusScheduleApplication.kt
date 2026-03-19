package com.wbjang.data_persistence_room_codelab_practice

import android.app.Application
import com.wbjang.data_persistence_room_codelab_practice.data.AppContainer
import com.wbjang.data_persistence_room_codelab_practice.data.AppDataContainer

class BusScheduleApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)

    }
}