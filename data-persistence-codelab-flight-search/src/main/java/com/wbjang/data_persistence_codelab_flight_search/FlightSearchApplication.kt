package com.wbjang.data_persistence_codelab_flight_search

import android.app.Application
import com.wbjang.data_persistence_codelab_flight_search.data.AppContainer
import com.wbjang.data_persistence_codelab_flight_search.data.AppDataContainer

class FlightSearchApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}