package com.wbjang.data_persistence_codelab_flight_search.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FlightSearchAppScreen() {
    val flightSearchAppViewModel: FlightSearchAppViewModel = viewModel(factory = FlightSearchAppViewModel.Factory)

    val flightSearchUiState = flightSearchAppViewModel.flightSearchUiState.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(text = "flightSearchUiState.value.size = ${flightSearchUiState.value.size}")
        }

    }
}