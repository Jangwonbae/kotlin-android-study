package com.wbjang.coroutines_rest_coil_codelab_bookshelf.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.R
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.network.BookItem
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.network.VolumeInfo
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.ui.theme.AndroidStudyTheme

@Composable
fun HomeScreen(
    books: List<BookItem>,
    onBookClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(books, key = { book -> book.id }) { book ->
            BookCard(
                id = book.id,
                book = book.volumeInfo,
                onCardClick = { onBookClick(book.id) }
            )
        }
    }
}

@Composable
fun BookCard(
    id: String,
    book: VolumeInfo,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCardClick() },
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(book.imageLinks?.thumbnail?.replace("http:", "https:"))
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = book.description,
                contentScale = ContentScale.Crop,
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
                        style = MaterialTheme.typography.bodySmall
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
        // Preview code
    }
}
