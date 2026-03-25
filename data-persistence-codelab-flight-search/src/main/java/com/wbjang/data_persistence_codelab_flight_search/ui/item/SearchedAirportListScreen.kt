package com.wbjang.data_persistence_codelab_flight_search.ui.item

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wbjang.data_persistence_codelab_flight_search.R
import com.wbjang.data_persistence_codelab_flight_search.data.FlightDetail
import com.wbjang.data_persistence_codelab_flight_search.ui.FlightSearchAppBody
import com.wbjang.data_persistence_codelab_flight_search.ui.SearchMode
import com.wbjang.data_persistence_codelab_flight_search.ui.item.common.AirportList
import com.wbjang.data_persistence_codelab_flight_search.ui.navigation.NavigationDestination
import com.wbjang.data_persistence_codelab_flight_search.ui.theme.AndroidStudyTheme
object SearchedAirportListDestination: NavigationDestination {
    override val route = "searched_airport_list"
    const val iataCodeArg = "iataCode"
    // 인자를 포함한 전체 경로 정의
    val routeWithArgs = "$route/{$iataCodeArg}"
}

@Composable
fun SearchedAirportListScreen(
    searchQuery: String,
    onClearSelectedAirport: () -> Unit,
    modifier: Modifier = Modifier,
    searchedAirportListViewModel: SearchedAirportListViewModel = viewModel(factory = SearchedAirportListViewModel.Factory)
) {
    val initialQuery = remember { searchQuery }
    LaunchedEffect(searchQuery) {
        // 처음 진입할 때의 쿼리와 현재 쿼리가 다를 경우에만 초기화 함수 호출
        if (searchQuery != initialQuery) {
            onClearSelectedAirport()
        }
    }
    val departureAirport by searchedAirportListViewModel.departureAirport.collectAsState()

    val flightDetails by searchedAirportListViewModel.flightDetails.collectAsState()
    
    BackHandler {
        onClearSelectedAirport()
    }

    AirportList(
        listName = stringResource(R.string.airport_list_flights_from) + " ${departureAirport?.iataCode}",
        flightDetails = flightDetails,
        onFavoriteClick = { depart, dest, isFav ->
            // ViewModel의 함수 호출
            searchedAirportListViewModel.toggleFavorite(depart, dest, isFav)
        }
    )
}
@Preview(showBackground = true)
@Composable
fun SearchedAirportListScreenPreview() {
    AndroidStudyTheme(dynamicColor = false) {
        FlightSearchAppBody(
            searchQuery = "",
            searchMode = SearchMode.FAVORITE,
            onQueryChange = {},
            modifier = Modifier.padding(10.dp),
            selectedIataCode = "",
            onAirportClick = {},
            onClearSelectedAirport = { }
        )
    }
}