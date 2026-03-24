package com.wbjang.data_persistence_codelab_flight_search.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wbjang.data_persistence_codelab_flight_search.R
import com.wbjang.data_persistence_codelab_flight_search.data.Favorite
import com.wbjang.data_persistence_codelab_flight_search.ui.item.FavoriteAirportList
import com.wbjang.data_persistence_codelab_flight_search.ui.theme.AndroidStudyTheme

@Composable
fun FlightSearchAppScreen(
    modifier: Modifier = Modifier,
    viewModel: FlightSearchAppViewModel = viewModel(factory = FlightSearchAppViewModel.Factory)
) {
    val flightSearchUiState by viewModel.flightSearchUiState.collectAsState()

    Scaffold(
        topBar = { FlightSearchAppBar() },
        modifier = modifier
            .fillMaxSize()
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            ) {
            FlightSearchAppBody(
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        modifier = modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun FlightSearchAppBody(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SearchBar()
        FavoriteAirportList()

    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }

    TextField(
        value = query,
        onValueChange = { query = it },
        modifier = modifier
            .fillMaxWidth(),
//            .height(65.dp),
        placeholder = {
            Text(
                text = stringResource(R.string.search_bar_label),
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        leadingIcon = {
            IconButton(
                onClick = {},
                modifier = Modifier.padding(10.dp),
                content = { Icon(Icons.Default.Search, contentDescription = null) }
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {},
                modifier = Modifier.padding(10.dp),
                content = { Icon(Icons.Filled.Close, contentDescription = null) }
            )
        },
//        keyboardOptions = TODO(),
//        keyboardActions = TODO(),
        singleLine = true,
//        interactionSource = TODO(),
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,    // 포커스 되었을 때 밑줄
            unfocusedIndicatorColor = Color.Transparent,  // 포커스 없을 때 밑줄
            disabledIndicatorColor = Color.Transparent,   // 비활성화 시 밑줄
            errorIndicatorColor = Color.Transparent,      // 에러 발생 시 밑줄

            // 필요하다면 배경색도 투명하게 설정 (선택 사항)
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun FlightSearchAppScreenPreview() {
    AndroidStudyTheme(dynamicColor = false) {
        FlightSearchAppBody(modifier = Modifier.padding(10.dp))
    }
}