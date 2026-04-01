package com.wbjang.data_persistence_codelab_flight_search.di

import com.wbjang.data_persistence_codelab_flight_search.data.FlightSearchRepository
import com.wbjang.data_persistence_codelab_flight_search.data.OfflineFlightSearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFlightSearchRepository(
        offlineFlightSearchRepository: OfflineFlightSearchRepository
    ): FlightSearchRepository
}