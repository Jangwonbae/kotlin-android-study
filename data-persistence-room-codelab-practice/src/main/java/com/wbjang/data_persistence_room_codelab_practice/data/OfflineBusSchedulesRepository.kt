package com.wbjang.data_persistence_room_codelab_practice.data

import kotlinx.coroutines.flow.Flow

class OfflineBusSchedulesRepository(private val busScheduleDao: BusScheduleDao): BusSchedulesRepository {
    override fun getAllBusSchedules(): Flow<List<BusSchedule>> = busScheduleDao.getAll()
    override fun getBusScheduleFor(stopName: String): Flow<List<BusSchedule>> = busScheduleDao.getByStopName(stopName)
}