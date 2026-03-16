package com.wbjang.coroutines_rest_coil_codelab_bookshelf.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("key") apiKey: String
    ) : Response<BookResponse>
}