package com.wbjang.coroutines_rest_codelab2.fake

import com.wbjang.coroutines_rest_codelab2.data.NetworkMarsPhotosRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NetworkMarsRepositoryTest {
    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() =
        runTest {
            val repository = NetworkMarsPhotosRepository(
                marsApiService = FakeMarsApiService()
            )
            assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
        }
//        {
//        val repository = NetworkMarsPhotosRepository(
//            marsApiService = FakeMarsApiService()
//        )
//        assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
//    }
}