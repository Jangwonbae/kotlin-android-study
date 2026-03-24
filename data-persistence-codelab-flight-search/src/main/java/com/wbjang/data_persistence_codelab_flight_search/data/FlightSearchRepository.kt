package com.wbjang.data_persistence_codelab_flight_search.data

import kotlinx.coroutines.flow.Flow

interface FlightSearchRepository {
    fun getAllAirportsStream(): Flow<List<Airport>>

    fun searchAirports(query: String) : Flow<List<Airport>>

}