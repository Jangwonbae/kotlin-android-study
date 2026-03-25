package com.wbjang.data_persistence_codelab_flight_search.ui.preview

import com.wbjang.data_persistence_codelab_flight_search.data.Airport
import com.wbjang.data_persistence_codelab_flight_search.data.FlightDetail

object SampleDataProvider {
    val airport1 = Airport(1, "ICN", "Incheon International Airport", 100000)
    val airport2 = Airport(2, "SFO", "San Francisco International Airport", 80000)
    val airport3 = Airport(3, "LHR", "London Heathrow Airport", 90000)
    val airport4 = Airport(4, "FCO", "Leonardo da Vinci International Airport", 70000)

    val airportList = listOf(airport1, airport2, airport3, airport4)

    val flightDetail1 = FlightDetail(airport1, airport2, isFavorite = true)
    val flightDetail2 = FlightDetail(airport1, airport3, isFavorite = false)
    val flightDetail3 = FlightDetail(airport4, airport2, isFavorite = false)

    val flightDetails = listOf(flightDetail1, flightDetail2, flightDetail3)
}