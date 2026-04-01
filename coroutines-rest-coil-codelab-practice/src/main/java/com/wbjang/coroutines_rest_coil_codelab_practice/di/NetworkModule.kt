package com.wbjang.coroutines_rest_coil_codelab_practice.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.wbjang.coroutines_rest_coil_codelab_practice.network.AmphibiansApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideAmphibiansApiService(retrofit: Retrofit): AmphibiansApiService {
        return retrofit.create(AmphibiansApiService::class.java)
    }
}
