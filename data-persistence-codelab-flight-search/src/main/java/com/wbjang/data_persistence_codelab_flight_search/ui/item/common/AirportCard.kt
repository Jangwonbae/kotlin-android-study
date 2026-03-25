package com.wbjang.data_persistence_codelab_flight_search.ui.item.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wbjang.data_persistence_codelab_flight_search.R
import com.wbjang.data_persistence_codelab_flight_search.data.Airport

@Composable
fun AirportCard(
    departureAirport: Airport,
    arrivalAirport: Airport,
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

                AirportInfoText(iataCode = departureAirport.iataCode, airportName = departureAirport.name)
                Text(
                    text = stringResource(R.string.airport_card_arrive),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp
                )
                AirportInfoText(iataCode = arrivalAirport.iataCode, airportName = arrivalAirport.name)
            }
            IconButton(
                modifier = Modifier.size(36.dp),
                onClick = {},
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.favorite_star_icon_off_24),
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
        departureAirport = Airport(id=1, iataCode = "FCO", name = "Leonardo da Vinci International Airport", passengers = 100),
        arrivalAirport = Airport(id=2, iataCode = "ICH", name = "Incheon Airport", passengers = 101))
}