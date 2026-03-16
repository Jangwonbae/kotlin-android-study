package com.wbjang.coroutines_rest_coil_codelab_bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.R
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.network.BookItem
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.network.VolumeInfo
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.ui.theme.AndroidStudyTheme

@Composable
fun HomeScreen(
    books: List<BookItem>,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(books) { book ->
            BookCard(book = book.volumeInfo)
        }
    }
}

@Composable
fun BookCard(
    book : VolumeInfo,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(108.dp)
            )
            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = book.title
                )

                book.authors?.forEach { author ->
                    Text(
                        text = author,
                        style = androidx.compose.material3.MaterialTheme.typography.bodySmall // 선택: 저자 스타일 적용
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AndroidStudyTheme {
//        HomeScreen(
//            listOf(
//                Book("책 제목1", "책 저자1"),
//                Book("책 제목2", "책 저자2"),
//                Book("책 제목3", "책 저자3"),
//                Book("책 제목4", "책 저자4"))
//            ,
//            modifier = Modifier.fillMaxSize().padding(start = 8.dp, end = 8.dp))
    }
}