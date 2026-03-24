package com.wbjang.data_persistence_codelab_flight_search.ui.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wbjang.data_persistence_codelab_flight_search.ui.FlightSearchAppBody
import com.wbjang.data_persistence_codelab_flight_search.ui.item.common.AirportCard
import com.wbjang.data_persistence_codelab_flight_search.ui.item.common.AirportInfoText
import com.wbjang.data_persistence_codelab_flight_search.ui.theme.AndroidStudyTheme

@Composable
fun RecommendedAirportList(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(10) {
            AirportInfoText(
                modifier = Modifier.clickable(
                  onClick = {}
                ),
                iataCode = "SVO",
                airportName = "Leonardo da Vinci International Airport "
            )
        }
    }

}
@Preview(showBackground = true)
@Composable
fun RecommendedAirportListScreenPreview() {
    AndroidStudyTheme(dynamicColor = false) {
        FlightSearchAppBody(modifier = Modifier.padding(10.dp),
            example = 3)
    }
}