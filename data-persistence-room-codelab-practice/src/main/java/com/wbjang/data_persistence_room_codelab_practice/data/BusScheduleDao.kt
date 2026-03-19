package com.wbjang.data_persistence_room_codelab_practice.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDao {
    @Query("SELECT * FROM schedule ORDER BY arrivalTimeInMillis")
    fun getAll(): Flow<List<BusSchedule>>

    @Query("SELECT * FROM schedule WHERE stopName = :stopName ORDER BY arrivalTimeInMillis")
    fun getByStopName(stopName: String): Flow<List<BusSchedule>>
}