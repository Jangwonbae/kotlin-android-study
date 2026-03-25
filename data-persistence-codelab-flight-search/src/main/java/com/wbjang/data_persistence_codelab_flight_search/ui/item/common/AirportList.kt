package com.wbjang.data_persistence_codelab_flight_search.ui.item.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wbjang.data_persistence_codelab_flight_search.data.FlightDetail

@Composable
fun AirportList(
    listName: String,
    flightDetails: List<FlightDetail>,
    onFavoriteClick: (String, String, Boolean) -> Unit,
    modifier: Modifier = Modifier,
    ) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = listName,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = flightDetails) { flightDetail ->
                AirportCard(
                    flightDetail = flightDetail,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
    }
}