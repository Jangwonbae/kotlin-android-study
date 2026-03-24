package com.wbjang.data_persistence_codelab_flight_search.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
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
import com.wbjang.data_persistence_codelab_flight_search.ui.FlightSearchAppBody
import com.wbjang.data_persistence_codelab_flight_search.ui.theme.AndroidStudyTheme

@Composable
fun FavoriteAirportList(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = stringResource(R.string.airport_list_favorite_routes),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(10) {
                AirportCard()
            }
        }
    }
}

@Composable
fun AirportCard(
    modifier: Modifier = Modifier,
//    favorite: Favorite
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "CODE",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "FavoriteAirportCard",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 12.sp
                    )
                }

                Text(
                    text = stringResource(R.string.airport_card_arrive),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "CODE",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "FavoriteAirportCard",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 12.sp
                    )
                }
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
fun FlightSearchAppScreenPreview() {
    AndroidStudyTheme(dynamicColor = false) {
        FlightSearchAppBody(modifier = Modifier.padding(10.dp))
    }
}