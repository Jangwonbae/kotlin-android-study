package com.wbjang.data_persistence_codelab_flight_search.ui.item.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wbjang.data_persistence_codelab_flight_search.R
import com.wbjang.data_persistence_codelab_flight_search.data.Airport
import com.wbjang.data_persistence_codelab_flight_search.data.FlightDetail

@Composable
fun AirportCard(
    flightDetail: FlightDetail,
    onFavoriteClick: (String, String, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = stringResource(R.string.airport_card_depart),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp
                )

                AirportInfoText(iataCode = flightDetail.departureAirport.iataCode, airportName = flightDetail.departureAirport.name)
                Text(
                    text = stringResource(R.string.airport_card_arrive),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp
                )
                AirportInfoText(iataCode = flightDetail.arrivalAirport.iataCode, airportName = flightDetail.arrivalAirport.name)
            }
            IconButton(
                modifier = Modifier.size(36.dp),
                onClick = {
                    onFavoriteClick(
                        flightDetail.departureAirport.iataCode,
                        flightDetail.arrivalAirport.iataCode,
                        flightDetail.isFavorite
                    )
                },
                content = {
                    Icon(
                        painter = painterResource(
                            id = if(flightDetail.isFavorite) R.drawable.ic_star_favorite_on_24
                            else R.drawable.ic_star_favorite_off_24
                        ),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        tint = Color.Unspecified
                    )
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AirportCardPreview() {
    AirportCard(
        FlightDetail(
            departureAirport = Airport(
                id = 1,
                iataCode = "FCO",
                name = "Leonardo da Vinci International Airport",
                passengers = 100
            ),
            arrivalAirport = Airport(
                id = 2,
                iataCode = "ICH",
                name = "Incheon Airport",
                passengers = 101
            )
        ),
        onFavoriteClick = { _, _, _ -> }
    )
}