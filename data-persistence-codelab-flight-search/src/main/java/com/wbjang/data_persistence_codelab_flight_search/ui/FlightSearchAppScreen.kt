package com.wbjang.data_persistence_codelab_flight_search.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.wbjang.data_persistence_codelab_flight_search.R
import com.wbjang.data_persistence_codelab_flight_search.ui.item.RecommendedAirportListDestination
import com.wbjang.data_persistence_codelab_flight_search.ui.navigation.FlightSearchNavHost
import com.wbjang.data_persistence_codelab_flight_search.ui.theme.AndroidStudyTheme

@Composable
fun FlightSearchAppScreen(
    modifier: Modifier = Modifier,
    flightSearchAppViewModel: FlightSearchAppViewModel = viewModel(factory = FlightSearchAppViewModel.Factory)
) {
    val searchQuery by flightSearchAppViewModel.searchQuery.collectAsState()
    val searchMode by flightSearchAppViewModel.searchMode.collectAsState()
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
                searchQuery = searchQuery,
                searchMode = searchMode,
                onQueryChange = { flightSearchAppViewModel.updateSearchQuery(it) },
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
    searchQuery: String,
    searchMode: SearchMode,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    LaunchedEffect(searchMode) {
        when (searchMode) {
            SearchMode.RECOMMENDED -> {
                navController.navigate(RecommendedAirportListDestination.route) {
                    launchSingleTop = true
                }
            }
            SearchMode.FAVORITE -> {
                if (navController.previousBackStackEntry != null) {
                    navController.popBackStack()
                }
            }
        }
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        SearchBar(
            query = searchQuery,
            onQueryChange = onQueryChange
        )
        FlightSearchNavHost(
            navController = navController,
            searchQuery = searchQuery,
            onNavigateBack = {
                onQueryChange("")
                navController.popBackStack()
                /*TODO: 뒤로 가기 정리*/
            }
        )
    }
}

@Composable
fun SearchBar(
    query : String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }


    BackHandler(enabled = isFocused) {
        // 뒤로 가기를 눌렀을 때 포커스를 해제함
        focusManager.clearFocus()
    }
    TextField(
        value = query,
        onValueChange = {
            onQueryChange(it)
        },
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onFocusChanged { state ->
                // 포커스 상태를 추적하여 BackHandler의 활성화 여부 결정
                isFocused = state.isFocused
            },
        textStyle = MaterialTheme.typography.bodyMedium, // 입력 텍스트 스타일 적용
        placeholder = {
            Text(
                text = stringResource(R.string.search_bar_label),
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        leadingIcon = {
            if(query.isEmpty()){
                IconButton(
                    onClick = {focusRequester.requestFocus()},
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(48.dp),
                            content = {
                        Icon(Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        ) }
                )
            }else {
                Spacer(modifier = Modifier.width(48.dp))
            }
        },

        trailingIcon = {
            if(query.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onQueryChange("")
                    },
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(48.dp),
                    content = {
                        Icon(Icons.Filled.Close,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        singleLine = true,
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun FlightSearchAppScreenPreview() {
    AndroidStudyTheme(dynamicColor = false) {
        FlightSearchAppBody(
            searchQuery = "",
            searchMode = SearchMode.FAVORITE,
            onQueryChange = {},
            modifier = Modifier.padding(10.dp))
    }
}