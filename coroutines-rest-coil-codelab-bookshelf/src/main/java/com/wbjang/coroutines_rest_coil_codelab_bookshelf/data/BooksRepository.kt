package com.wbjang.coroutines_rest_coil_codelab_bookshelf.data

import com.wbjang.coroutines_rest_coil_codelab_bookshelf.BuildConfig
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.network.BookResponse
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.network.BooksApiService
import retrofit2.Response

interface BooksRepository {
    suspend fun getBooks(query: String): Response<BookResponse>
}

class NetworkBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {
    override suspend fun getBooks(query: String): Response<BookResponse> {
        return booksApiService.getBooks(query, BuildConfig.GOOGLE_BOOKS_API_KEY)
    }
}