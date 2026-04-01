package com.wbjang.coroutines_rest_coil_codelab_practice.di

import com.wbjang.coroutines_rest_coil_codelab_practice.data.AmphibiansRepository
import com.wbjang.coroutines_rest_coil_codelab_practice.data.NetworkAmphibiansRepository
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
    abstract fun bindAmphibiansRepository(
        networkAmphibiansRepository: NetworkAmphibiansRepository
    ): AmphibiansRepository
}