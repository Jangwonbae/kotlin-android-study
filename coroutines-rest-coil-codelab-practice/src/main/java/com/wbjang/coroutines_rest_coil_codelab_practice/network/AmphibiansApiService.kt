package com.wbjang.coroutines_rest_coil_codelab_practice.network

import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}