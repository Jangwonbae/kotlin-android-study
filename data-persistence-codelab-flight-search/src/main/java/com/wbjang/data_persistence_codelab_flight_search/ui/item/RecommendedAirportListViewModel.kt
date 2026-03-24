package com.wbjang.data_persistence_codelab_flight_search.ui.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.wbjang.data_persistence_codelab_flight_search.FlightSearchApplication
import com.wbjang.data_persistence_codelab_flight_search.data.Airport
import com.wbjang.data_persistence_codelab_flight_search.data.FlightSearchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class RecommendedAirportListViewModel(
    private val flightSearchRepository: FlightSearchRepository
): ViewModel() {
    private val _searchQuery = MutableStateFlow("")

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val recommendedAirports: StateFlow<List<Airport>> = _searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            // 여기서 Repository 함수에 '순수 문자열'인 query를 전달합니다.
            flightSearchRepository.searchAirports(query)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = listOf()
        )

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightSearchApplication)
                val flightSearchRepository = application.container.flightSearchRepository
                RecommendedAirportListViewModel(flightSearchRepository)
            }
        }
    }
}