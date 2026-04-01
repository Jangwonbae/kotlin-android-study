package com.wbjang.data_persistence_codelab_flight_search.ui.item

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wbjang.data_persistence_codelab_flight_search.data.Airport
import com.wbjang.data_persistence_codelab_flight_search.ui.FlightSearchAppBody
import com.wbjang.data_persistence_codelab_flight_search.ui.SearchMode
import com.wbjang.data_persistence_codelab_flight_search.ui.item.common.AirportInfoText
import com.wbjang.data_persistence_codelab_flight_search.ui.navigation.NavigationDestination
import com.wbjang.data_persistence_codelab_flight_search.ui.preview.SampleDataProvider
import com.wbjang.data_persistence_codelab_flight_search.ui.theme.AndroidStudyTheme
object RecommendedAirportListDestination: NavigationDestination {
    override val route = "recommended_airport_list"
}
@Composable
fun RecommendedAirportListScreen(
    searchQuery: String,
    onNavigateSearchedAirportList: (String) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    recommendedAirportListViewModel: RecommendedAirportListViewModel = hiltViewModel()
) {
    BackHandler {
        onNavigateBack()
    }
    LaunchedEffect(searchQuery) {
        recommendedAirportListViewModel.updateSearchQuery(searchQuery)
    }
    val recommendedAirports by recommendedAirportListViewModel.recommendedAirports.collectAsState()
    RecommendedAirportList(
        recommendedAirports = recommendedAirports,
        onNavigateSearchedAirportList = onNavigateSearchedAirportList,
        modifier = modifier
        )
}
@Composable
fun RecommendedAirportList(
    recommendedAirports: List<Airport>,
    onNavigateSearchedAirportList: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(
            items = recommendedAirports,
            key = { it.id }
        ) { airport->
            AirportInfoText(
                modifier = Modifier
                    .clickable {onNavigateSearchedAirportList(airport.iataCode)}
                    .padding(vertical = 5.dp, horizontal = 4.dp),
                iataCode = airport.iataCode,
                airportName = airport.name
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun RecommendedAirportListScreenPreview() {
    AndroidStudyTheme(dynamicColor = false) {
        RecommendedAirportList(
            recommendedAirports = SampleDataProvider.airportList,
            onNavigateSearchedAirportList = {}
        )
    }
}