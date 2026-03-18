package com.wbjang.data_persistence_room_codelab

import android.app.Application
import com.wbjang.data_persistence_room_codelab.data.AppContainer
import com.wbjang.data_persistence_room_codelab.data.AppDataContainer

class InventoryApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
