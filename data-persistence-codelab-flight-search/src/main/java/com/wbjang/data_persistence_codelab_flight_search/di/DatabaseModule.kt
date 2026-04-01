package com.wbjang.data_persistence_codelab_flight_search.di

import android.content.Context
import androidx.room.Room
import com.wbjang.data_persistence_codelab_flight_search.data.AirportDao
import com.wbjang.data_persistence_codelab_flight_search.data.FavoriteDao
import com.wbjang.data_persistence_codelab_flight_search.data.FlightSearchDatabase
import com.wbjang.data_persistence_codelab_flight_search.data.FlightSearchRepository
import com.wbjang.data_persistence_codelab_flight_search.data.OfflineFlightSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FlightSearchDatabase {
        return Room.databaseBuilder(
            context,
            FlightSearchDatabase::class.java,
            "flight_search_database"
        )
            .createFromAsset("flight_search.db")
            .build()
    }

    @Provides
    fun provideAirportDao(database: FlightSearchDatabase) : AirportDao {
        return database.airportDao()
    }

    @Provides
    fun provideFavoriteDao(database: FlightSearchDatabase) : FavoriteDao {
        return database.favoriteDao()
    }
}