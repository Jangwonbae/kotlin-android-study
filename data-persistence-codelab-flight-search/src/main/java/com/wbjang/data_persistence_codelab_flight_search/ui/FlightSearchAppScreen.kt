package com.wbjang.data_persistence_codelab_flight_search.ui

import android.util.Log
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
import androidx.compose.ui.focus.FocusManager
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
import com.wbjang.data_persistence_codelab_flight_search.ui.item.FavoriteAirportListDestination
import com.wbjang.data_persistence_codelab_flight_search.ui.item.RecommendedAirportListDestination
import com.wbjang.data_persistence_codelab_flight_search.ui.item.SearchedAirportListDestination
import com.wbjang.data_persistence_codelab_flight_search.ui.navigation.FlightSearchNavHost
import com.wbjang.data_persistence_codelab_flight_search.ui.theme.AndroidStudyTheme
import com.wbjang.data_persistence_codelab_flight_search.util.LogUtil

private const val TAG = "FlightSearchAppScreen"
@Composable
fun FlightSearchAppScreen(
    modifier: Modifier = Modifier,
    flightSearchAppViewModel: FlightSearchAppViewModel = viewModel(factory = FlightSearchAppViewModel.Factory)
) {
    val searchQuery by flightSearchAppViewModel.searchQuery.collectAsState()
    val selectedIataCode by flightSearchAppViewModel.selectedIataCode.collectAsState()
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
                selectedIataCode = selectedIataCode,
                searchMode = searchMode,
                onQueryChange = { flightSearchAppViewModel.updateSearchQuery(it) },
                onAirportClick = { iataCode -> flightSearchAppViewModel.selectAirport(iataCode) },
                onClearSelectedAirport = { flightSearchAppViewModel.clearSelectedAirport() },
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
    selectedIataCode: String?,
    searchMode: SearchMode,
    onQueryChange: (String) -> Unit,
    onAirportClick: (String) -> Unit,
    onClearSelectedAirport: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val focusManager = LocalFocusManager.current

    // 네비게이션 로직을 한 곳(LaunchedEffect)에서 관리하여 중복 popBackStack을 방지합니다.
    LaunchedEffect(searchMode) {
        LogUtil.d("searchMode = ${searchMode.name}")
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        when (searchMode) {
            SearchMode.RECOMMENDED -> {
                if (currentRoute == SearchedAirportListDestination.routeWithArgs) {
                    navController.popBackStack()
                }
                else if (currentRoute != RecommendedAirportListDestination.route) {
                    navController.navigate(RecommendedAirportListDestination.route) {
                        launchSingleTop = true
                    }
                }
            }
            SearchMode.FAVORITE -> {
                focusManager.clearFocus()
                navController.popBackStack(FavoriteAirportListDestination.route, inclusive = false)
            }
            SearchMode.SEARCHED -> {
                if (currentRoute != SearchedAirportListDestination.routeWithArgs) {
                    selectedIataCode?.let { iataCode ->
                        focusManager.clearFocus()
                        navController.navigate("${SearchedAirportListDestination.route}/$iataCode") {
                            launchSingleTop = true
                        }
                    }
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
            onQueryChange = onQueryChange,
            focusManager = focusManager
        )
        FlightSearchNavHost(
            navController = navController,
            searchQuery = searchQuery,
            onNavigateSearchedAirportList = { searchedIataCode-> onAirportClick(searchedIataCode)},
            onClearSelectedAirport = onClearSelectedAirport,
            onNavigateBack = {
                onQueryChange("")
            }
        )
    }
}

@Composable
fun SearchBar(
    query : String,
    onQueryChange: (String) -> Unit,
    focusManager: FocusManager = LocalFocusManager.current,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }

    var isFocused by remember { mutableStateOf(false) }

    // 포커스가 있을 때만 뒤로 가기를 가로채서 포커스를 해제합니다.
    BackHandler(enabled = isFocused) {
        focusManager.clearFocus()
    }

    TextField(
        value = query,
        onValueChange = { onQueryChange(it) },
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onFocusChanged { state -> isFocused = state.isFocused },
        textStyle = MaterialTheme.typography.bodyMedium,
        placeholder = {
            Text(
                text = stringResource(R.string.search_bar_label),
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        leadingIcon = {
            if(query.isEmpty()){
                IconButton(
                    onClick = { focusRequester.requestFocus() },
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(48.dp)
                ) {
                    Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(24.dp))
                }
            } else {
                Spacer(modifier = Modifier.width(48.dp))
            }
        },
        trailingIcon = {
            if(query.isNotEmpty()) {
                IconButton(
                    onClick = { onQueryChange("") },
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(48.dp)
                ) {
                    Icon(Icons.Filled.Close, contentDescription = null, modifier = Modifier.size(24.dp))
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        singleLine = true,
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun FlightSearchAppScreenPreview() {
    AndroidStudyTheme(dynamicColor = false) {
//        FlightSearchAppBody(
//            searchQuery = "",
//            searchMode = SearchMode.FAVORITE,
//            onQueryChange = {},
//            modifier = Modifier.padding(10.dp)
//        )
    }
}