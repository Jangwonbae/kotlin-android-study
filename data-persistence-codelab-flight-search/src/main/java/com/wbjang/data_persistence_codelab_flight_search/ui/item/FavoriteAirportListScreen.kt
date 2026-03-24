package com.wbjang.data_persistence_codelab_flight_search.ui.item

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wbjang.data_persistence_codelab_flight_search.R
import com.wbjang.data_persistence_codelab_flight_search.ui.FlightSearchAppBody
import com.wbjang.data_persistence_codelab_flight_search.ui.item.common.AirportList
import com.wbjang.data_persistence_codelab_flight_search.ui.navigation.NavigationDestination
import com.wbjang.data_persistence_codelab_flight_search.ui.theme.AndroidStudyTheme

object FavoriteAirportListDestination: NavigationDestination {
    override val route = "favorite_airport_list"
}

@Composable
fun FavoriteAirportListScreen(){
    AirportList(
        listName = stringResource(R.string.airport_list_favorite_routes)
    )
}

@Preview(showBackground = true)
@Composable
fun FavoriteAirportListScreenPreview() {
    AndroidStudyTheme(dynamicColor = false) {
        FlightSearchAppBody(modifier = Modifier.padding(10.dp))
    }
}