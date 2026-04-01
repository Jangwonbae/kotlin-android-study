package com.wbjang.data_persistence_codelab_flight_search.ui

import androidx.compose.ui.geometry.isEmpty
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.wbjang.data_persistence_codelab_flight_search.FlightSearchApplication
import com.wbjang.data_persistence_codelab_flight_search.data.FlightSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

enum class SearchMode {
    FAVORITE, RECOMMENDED, SEARCHED
}
@HiltViewModel
class FlightSearchAppViewModel @Inject constructor(flightSearchRepository: FlightSearchRepository): ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery
    private val _selectedIataCode = MutableStateFlow<String?>(null)
    val selectedIataCode: StateFlow<String?> = _selectedIataCode

    val searchMode: StateFlow<SearchMode> = combine(_searchQuery, _selectedIataCode) { query, code ->
        when {
            code != null -> SearchMode.SEARCHED
            query.isEmpty() -> SearchMode.FAVORITE
            else -> SearchMode.RECOMMENDED
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SearchMode.FAVORITE
    )
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
    fun selectAirport(iataCode: String) {
        _selectedIataCode.value = iataCode
    }
    fun clearSelectedAirport() {
        _selectedIataCode.value = null
    }
}
