package com.wbjang.data_persistence_codelab_flight_search.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.jvm.java

@Database(entities = [AirPort::class, Favorite::class], version = 1, exportSchema = false)
abstract class FlightSearchDatabase: RoomDatabase() {

    abstract fun airPortDao(): AirPortDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FlightSearchDatabase? = null

        fun getDatabase(context: Context): FlightSearchDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context, FlightSearchDatabase::class.java, "flight_search_database"
                )
                    .createFromAsset("flight_search.db")
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}