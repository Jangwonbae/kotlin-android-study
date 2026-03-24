package com.wbjang.data_persistence_codelab_flight_search.data

import android.content.Context

interface AppContainer {
    val flightSearchRepository: FlightSearchRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val flightSearchRepository: FlightSearchRepository by lazy {
        OfflineFlightSearchRepository(
            FlightSearchDatabase.getDatabase(context).airportDao(),
            FlightSearchDatabase.getDatabase(context).favoriteDao()
        )
    }
}