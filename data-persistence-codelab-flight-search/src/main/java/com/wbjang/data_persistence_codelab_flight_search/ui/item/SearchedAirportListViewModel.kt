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
import com.wbjang.data_persistence_codelab_flight_search.data.Favorite
import com.wbjang.data_persistence_codelab_flight_search.data.FlightDetail
import com.wbjang.data_persistence_codelab_flight_search.data.FlightSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.emptyList
@HiltViewModel
class SearchedAirportListViewModel @Inject constructor(
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

    private val arrivalAirports: StateFlow<List<Airport>> = flightSearchRepository
        .getArrivalAirportsStream(iataCode)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
    private val favoriteAirports: StateFlow<List<Favorite>> = flightSearchRepository
        .getFavoriteAirportsStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
    val flightDetails: StateFlow<List<FlightDetail>> = combine(
        departureAirport,
        arrivalAirports,
        favoriteAirports
    ) { departure, arrivals, favorites ->
        if (departure == null) return@combine emptyList<FlightDetail>()

        arrivals.map { arrival ->
            val isFav = favorites.any {
                it.departureCode == departure.iataCode && it.destinationCode == arrival.iataCode
            }
            FlightDetail(departure, arrival, isFav)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )
    fun toggleFavorite(departureCode: String, destinationCode: String, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                flightSearchRepository.deleteFavorite(departureCode, destinationCode)
            } else {
                flightSearchRepository.insertFavorite(
                    Favorite(0, departureCode, destinationCode)
                )
            }
        }
    }
}