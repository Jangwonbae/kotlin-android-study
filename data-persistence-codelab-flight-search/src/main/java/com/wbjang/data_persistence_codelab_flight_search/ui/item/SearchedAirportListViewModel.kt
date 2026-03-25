package com.wbjang.data_persistence_codelab_flight_search.ui.item

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.wbjang.data_persistence_codelab_flight_search.FlightSearchApplication
import com.wbjang.data_persistence_codelab_flight_search.data.Airport
import com.wbjang.data_persistence_codelab_flight_search.data.FlightSearchRepository
import com.wbjang.data_persistence_codelab_flight_search.ui.FlightSearchAppViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlin.collections.emptyList

class SearchedAirportListViewModel(
    savedStateHandle: SavedStateHandle,
    private val flightSearchRepository: FlightSearchRepository
): ViewModel() {
    private val iataCode: String = checkNotNull(savedStateHandle[SearchedAirportListDestination.iataCodeArg])

    val departureAirport: StateFlow<Airport?> = flightSearchRepository
        .getAirportByCodeStream(iataCode)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val arrivalAirports: StateFlow<List<Airport>> = flightSearchRepository
        .getArrivalAirportsStream(iataCode)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightSearchApplication)
                val flightSearchRepository = application.container.flightSearchRepository
                val savedStateHandle = this.createSavedStateHandle()
                SearchedAirportListViewModel(
                    savedStateHandle = savedStateHandle,
                    flightSearchRepository = flightSearchRepository
                )
            }
        }
    }
}