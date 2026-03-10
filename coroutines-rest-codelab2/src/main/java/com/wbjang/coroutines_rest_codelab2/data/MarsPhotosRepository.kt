package com.wbjang.coroutines_rest_codelab2.data
import com.wbjang.coroutines_rest_codelab2.network.MarsApiService
import com.wbjang.coroutines_rest_codelab2.network.MarsPhoto

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetworkMarsPhotosRepository(
    private val marsApiService : MarsApiService
) : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return marsApiService.getPhotos()
    }
}