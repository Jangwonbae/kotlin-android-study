package com.wbjang.data_persistence_codelab_flight_search.data

import kotlinx.coroutines.flow.Flow

interface FlightSearchRepository {
    fun getAllAirportsStream(): Flow<List<Airport>>

    fun searchAirportsStream(query: String) : Flow<List<Airport>>

    fun getAirportByCodeStream(iataCode: String): Flow<Airport>

    fun getArrivalAirportsStream(departureCode: String): Flow<List<Airport>>

    fun getFavoriteAirportsStream(): Flow<List<Favorite>>

    suspend fun insertFavorite(favorite: Favorite)

    suspend fun deleteFavorite(departureCode: String, destinationCode: String)
}