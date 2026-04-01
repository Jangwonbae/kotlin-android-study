package com.wbjang.data_persistence_codelab_flight_search.ui.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.wbjang.data_persistence_codelab_flight_search.FlightSearchApplication
import com.wbjang.data_persistence_codelab_flight_search.data.Airport
import com.wbjang.data_persistence_codelab_flight_search.data.FlightSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RecommendedAirportListViewModel @Inject constructor(
    private val flightSearchRepository: FlightSearchRepository
): ViewModel() {
    private val _searchQuery = MutableStateFlow("")

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val recommendedAirports: StateFlow<List<Airport>> = _searchQuery
        .debounce { query ->
            if (query.isEmpty()) 0L else 300L
        }
        .flatMapLatest { query ->
            if (query.isEmpty()) {
                // 검색어가 비어있으면 DB 쿼리 없이 즉시 빈 리스트 반환
                flowOf(emptyList())
            } else {
                flightSearchRepository.searchAirportsStream(query)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = listOf()
        )

}