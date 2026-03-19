package com.wbjang.data_persistence_room_codelab_practice.data

import android.content.Context

interface AppContainer {
     val busSchedulesRepository: BusSchedulesRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val busSchedulesRepository: BusSchedulesRepository by lazy {
        OfflineBusSchedulesRepository(BusScheduleDatabase.getDatabase(context).busScheduleDao())
    }
}

