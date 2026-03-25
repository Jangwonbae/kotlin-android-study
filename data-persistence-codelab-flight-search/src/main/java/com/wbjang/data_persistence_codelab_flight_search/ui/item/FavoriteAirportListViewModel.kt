package com.wbjang.data_persistence_codelab_flight_search.ui.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.wbjang.data_persistence_codelab_flight_search.FlightSearchApplication
import com.wbjang.data_persistence_codelab_flight_search.data.Airport
import com.wbjang.data_persistence_codelab_flight_search.data.Favorite
import com.wbjang.data_persistence_codelab_flight_search.data.FlightDetail
import com.wbjang.data_persistence_codelab_flight_search.data.FlightSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoriteAirportListViewModel(
    private val flightSearchRepository: FlightSearchRepository
): ViewModel() {

    private val airportsMap: Flow<Map<String, Airport>> = flightSearchRepository.getAllAirportsStream()
        .map { list -> list.associateBy { it.iataCode } }

    val favoriteAirports: StateFlow<List<FlightDetail>> = combine(
        flightSearchRepository.getFavoriteAirportsStream(),//Flow<List<Favorite>>
        airportsMap//Flow<Map<String, Airport>>
    ){ favorites, airports ->//List<Favorite>, Map<String, Airport>
        favorites.mapNotNull { favorite ->
            val departure = airports[favorite.departureCode]
            val destination = airports[favorite.destinationCode]

            // 출발지와 도착지 공항 정보가 모두 존재할 때만 FlightDetail 생성
            if (departure != null && destination != null) {
                FlightDetail(
                    departureAirport = departure,
                    arrivalAirport = destination,
                    isFavorite = true // 즐겨찾기 목록이므로 항상 true
                )
            } else {
                null
            }
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
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightSearchApplication)
                val flightSearchRepository = application.container.flightSearchRepository
                FavoriteAirportListViewModel(flightSearchRepository)
            }
        }
    }
}