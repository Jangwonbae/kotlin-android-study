package com.wbjang.coroutines_rest_coil_codelab_practice.data

import com.wbjang.coroutines_rest_coil_codelab_practice.network.Amphibian
import com.wbjang.coroutines_rest_coil_codelab_practice.network.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class NetworkAmphibiansRepository(
    private val amphibianApiService : AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun getAmphibians(): List<Amphibian> {
        return amphibianApiService.getAmphibians()
    }
}