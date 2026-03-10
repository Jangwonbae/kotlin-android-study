package com.wbjang.coroutines_rest_codelab2.fake

import com.wbjang.coroutines_rest_codelab2.data.MarsPhotosRepository
import com.wbjang.coroutines_rest_codelab2.network.MarsPhoto

class FakeNetworkMarsPhotosRepository : MarsPhotosRepository{
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}