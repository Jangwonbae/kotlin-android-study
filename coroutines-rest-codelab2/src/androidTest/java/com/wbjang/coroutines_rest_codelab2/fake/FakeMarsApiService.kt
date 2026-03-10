package com.wbjang.coroutines_rest_codelab2.fake

import com.wbjang.coroutines_rest_codelab2.network.MarsApiService
import com.wbjang.coroutines_rest_codelab2.network.MarsPhoto

class FakeMarsApiService : MarsApiService {
    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}