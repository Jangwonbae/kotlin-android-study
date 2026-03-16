package com.wbjang.coroutines_rest_coil_codelab_bookshelf.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.network.BookItem


@Composable
fun DetailScreen(
    bookItem: BookItem?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = bookItem!!.volumeInfo.title)
        bookItem!!.volumeInfo.description?.let { text -> Text(text = text) }
    }
}