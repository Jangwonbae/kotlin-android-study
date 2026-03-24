package com.wbjang.data_persistence_codelab_flight_search.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wbjang.data_persistence_codelab_flight_search.ui.item.FavoriteAirportListDestination
import com.wbjang.data_persistence_codelab_flight_search.ui.item.FavoriteAirportListScreen
import com.wbjang.data_persistence_codelab_flight_search.ui.item.RecommendedAirportListDestination
import com.wbjang.data_persistence_codelab_flight_search.ui.item.RecommendedAirportListScreen
import com.wbjang.data_persistence_codelab_flight_search.ui.item.SearchedAirportListDestination
import com.wbjang.data_persistence_codelab_flight_search.ui.item.SearchedAirportListScreen

@Composable
fun FlightSearchNavHost(
    navController: NavHostController,
    searchQuery: String,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = FavoriteAirportListDestination.route
    ) {
        composable(route = FavoriteAirportListDestination.route){
            FavoriteAirportListScreen()
        }
        composable(route = SearchedAirportListDestination.route){
            SearchedAirportListScreen()
        }
        composable(route = RecommendedAirportListDestination.route){
            RecommendedAirportListScreen(
                searchQuery,
                onNavigateBack = onNavigateBack
            )
        }
    }
}