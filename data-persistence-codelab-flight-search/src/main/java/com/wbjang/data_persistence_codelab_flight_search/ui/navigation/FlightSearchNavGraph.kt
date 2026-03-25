package com.wbjang.data_persistence_codelab_flight_search.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
    onNavigateSearchedAirportList: (String) -> Unit,
    onClearSelectedAirport: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = FavoriteAirportListDestination.route,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { it }) + fadeIn()
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { -it }) + fadeOut()
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { -it }) + fadeIn()
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { it }) + fadeOut()
        }
    ) {
        composable(route = FavoriteAirportListDestination.route){
            FavoriteAirportListScreen()
        }
        composable(
            //navigate 시 인자 넘겨주기
            route = SearchedAirportListDestination.routeWithArgs,
            arguments = listOf(navArgument(SearchedAirportListDestination.iataCodeArg) { type = NavType.StringType })
        ) {
            SearchedAirportListScreen(
                searchQuery = searchQuery,
                onClearSelectedAirport = onClearSelectedAirport
            )
        }
        composable(route = RecommendedAirportListDestination.route){
            RecommendedAirportListScreen(
                searchQuery,
                onNavigateSearchedAirportList = { iataCode->
                    onNavigateSearchedAirportList(iataCode)
                },
                onNavigateBack = onNavigateBack
            )
        }
    }
}