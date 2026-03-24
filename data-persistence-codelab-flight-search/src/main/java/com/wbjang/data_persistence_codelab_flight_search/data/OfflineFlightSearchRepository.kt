package com.wbjang.data_persistence_codelab_flight_search.data

import kotlinx.coroutines.flow.Flow

class OfflineFlightSearchRepository(private val airportDao: AirportDao, private val favoriteDao: FavoriteDao) :FlightSearchRepository {
    override fun getAllAirportsStream(): Flow<List<Airport>> = airportDao.getAllItems()
}