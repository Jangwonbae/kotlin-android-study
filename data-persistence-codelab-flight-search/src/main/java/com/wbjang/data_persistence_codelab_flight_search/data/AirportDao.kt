package com.wbjang.data_persistence_codelab_flight_search.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Query("SELECT * from airport ORDER BY name ASC")
    fun getAllItems(): Flow<List<Airport>>

    @Query("SELECT * FROM airport WHERE UPPER(iata_code) LIKE UPPER('%' || :query || '%') OR UPPER(name) LIKE UPPER('%' || :query || '%')")
    fun searchAirports(query: String) : Flow<List<Airport>>

    @Query("SELECT * FROM airport WHERE iata_code = :iataCode")
    fun getAirportByCode(iataCode: String): Flow<Airport>

    @Query("SELECT * FROM airport WHERE iata_code != :departureCode ORDER BY passengers DESC")
    fun getArrivalAirports(departureCode: String): Flow<List<Airport>>


}