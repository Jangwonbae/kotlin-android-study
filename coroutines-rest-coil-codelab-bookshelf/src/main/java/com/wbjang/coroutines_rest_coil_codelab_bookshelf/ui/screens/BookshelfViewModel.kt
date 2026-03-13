package com.wbjang.coroutines_rest_coil_codelab_bookshelf.ui.screens

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.data.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Collections.list

class BookshelfViewModel : ViewModel() {
    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books : StateFlow<List<Book>> = _books.asStateFlow()

    init {
        resetBooks()
    }

    fun searchBooks(query : String) {
        if(query.isNotEmpty()) {
            _books.update { books ->
                books.filter{
                    book -> book.title.contains(query, ignoreCase = true)
                }
            }
        } else {
            resetBooks()
        }
    }
    fun resetBooks() {
        _books.value = List(20) {
                index-> Book(title = "책 제목 $index", authors = "책 저자 $index")
        }
    }
}