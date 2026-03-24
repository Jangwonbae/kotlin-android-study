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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wbjang.data_persistence_codelab_flight_search.R

@Composable
fun AirportCard(
    modifier: Modifier = Modifier,
//    favorite: Favorite
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
        )
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

                AirportInfoText(iataCode = "FCO", airportName = "Leonardo da Vinci International Airport")
                Text(
                    text = stringResource(R.string.airport_card_arrive),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp
                )
                AirportInfoText(iataCode = "FCO", airportName = "Leonardo da Vinci International Airport")
            }
            IconButton(
                modifier = Modifier.size(36.dp),
                onClick = {},
                content = {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        tint = Color.Yellow
                    )
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AirportCardPreview() {
    AirportCard()
}