package com.wbjang.coroutines_rest_coil_codelab_bookshelf.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.BookshelfApplication
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.data.BooksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.network.BookItem

class BookshelfViewModel(private val booksRepository: BooksRepository) : ViewModel() {
    private val _books = MutableStateFlow<List<BookItem>>(emptyList())
    val books : StateFlow<List<BookItem>> = _books.asStateFlow()

    init {
        getBooks("flowers+inauthor:keyes")
    }

    fun searchBooks(query : String) {

    }

    fun getBooks(query: String) {
        viewModelScope.launch {
            try {
                val response =  booksRepository.getBooks(query)
                if (response.isSuccessful) {
                    // 성공 시: 실제 데이터 클래스(BookResponse)를 가져옴
                    val bookData = response.body()
                    _books.value = bookData?.items ?: emptyList()
                } else {
                    // 실패 시: 404, 500 등 에러 코드 확인 가능
//                    val errorCode = response.code()
//                    Log.e("TAG", errorCode)
                }
            } catch (e: Exception) {
                Log.e("TAG", e.toString())
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val booksRepository = application.appContainer.booksRepository
                BookshelfViewModel(booksRepository = booksRepository)
            }
        }
    }
}