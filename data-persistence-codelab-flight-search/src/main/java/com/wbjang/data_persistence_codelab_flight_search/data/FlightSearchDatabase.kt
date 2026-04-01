package com.wbjang.data_persistence_codelab_flight_search.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.jvm.java

@Database(entities = [Airport::class, Favorite::class], version = 1, exportSchema = false)
abstract class FlightSearchDatabase: RoomDatabase() {

    abstract fun airportDao(): AirportDao
    abstract fun favoriteDao(): FavoriteDao

}