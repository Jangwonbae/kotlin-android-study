package com.wbjang.data_persistence_room_codelab_practice.data

import kotlinx.coroutines.flow.Flow

interface BusSchedulesRepository {
    fun getAllBusSchedules(): Flow<List<BusSchedule>>

    fun getBusScheduleFor(stopName: String): Flow<List<BusSchedule>>
}