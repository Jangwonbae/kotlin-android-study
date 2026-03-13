package com.wbjang.coroutines_rest_coil_codelab_bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.ui.screens.HomeScreen
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.ui.theme.AndroidStudyTheme

@Composable
fun BookshelfApp() {
    AndroidStudyTheme {
        Scaffold(modifier = Modifier
            .fillMaxSize(),
            topBar = { SearchBar() }
            ) {
            Surface(modifier = Modifier.padding(it)) {
                HomeScreen(modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp, end = 8.dp))
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    // 검색어 상태 관리
    var query by remember { mutableStateOf("") }

    TopAppBar(
        modifier = modifier,
        title = {
            TextField(
                value = query,
                onValueChange = {query = it},
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Search")
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    if(query.isNotEmpty()) {
                        IconButton(onClick = {query = ""}) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = null
                            )
                        }
                    }

                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )

        }
    )
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AndroidStudyTheme {
        BookshelfApp()
    }
}

