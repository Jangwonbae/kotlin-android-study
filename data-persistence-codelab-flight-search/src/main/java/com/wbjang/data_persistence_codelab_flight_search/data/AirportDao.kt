package com.wbjang.data_persistence_codelab_flight_search.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Query("SELECT * from airport ORDER BY name ASC")
    fun getAllItems(): Flow<List<Airport>>
}