package com.wbjang.football_api_practice.di

import com.wbjang.football_api_practice.repository.MatchRepository
import com.wbjang.football_api_practice.repository.NetworkMatchRepository
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
    abstract fun bindMatchRepository(
        networkMatchRepository: NetworkMatchRepository
    ): MatchRepository
}