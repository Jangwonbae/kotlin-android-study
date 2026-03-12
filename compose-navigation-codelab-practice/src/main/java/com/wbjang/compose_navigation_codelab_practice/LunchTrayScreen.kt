/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wbjang.compose_navigation_codelab_practice

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wbjang.compose_navigation_codelab_practice.datasource.DataSource
import com.wbjang.compose_navigation_codelab_practice.ui.AccompanimentMenuScreen
import com.wbjang.compose_navigation_codelab_practice.ui.CheckoutScreen
import com.wbjang.compose_navigation_codelab_practice.ui.EntreeMenuScreen
import com.wbjang.compose_navigation_codelab_practice.ui.OrderViewModel
import com.wbjang.compose_navigation_codelab_practice.ui.SideDishMenuScreen
import com.wbjang.compose_navigation_codelab_practice.ui.StartOrderScreen


enum class LunchTrayScreen(@StringRes val title:Int) {
    Start(R.string.app_name),
    Entree(R.string.choose_entree),
    Side(R.string.choose_side_dish),
    Checkout(R.string.order_checkout),
    Accompaniment(R.string.choose_accompaniment)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(currentScreenTitle)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = LunchTrayScreen.valueOf(
        backStackEntry?.destination?.route ?: LunchTrayScreen.Start.name
    )

    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()
    Scaffold(
        topBar = {
            LunchTrayAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {navController.navigateUp()},
                modifier = Modifier
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(navController = navController, startDestination = LunchTrayScreen.Start.name) {
            composable(route = LunchTrayScreen.Start.name) {
                StartOrderScreen(
                    onStartOrderButtonClicked = {navController.navigate(route = LunchTrayScreen.Entree.name)},
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }
            composable(route = LunchTrayScreen.Entree.name) {
                EntreeMenuScreen(
                    options = DataSource.entreeMenuItems,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
                    },
                    onNextButtonClicked = {
                        navController.navigate(route = LunchTrayScreen.Side.name)
                                          },
                    onSelectionChanged = { item ->
                        viewModel.updateEntree(item)
                    },
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }
            composable(route = LunchTrayScreen.Side.name) {
                SideDishMenuScreen(
                    options = DataSource.sideDishMenuItems,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
                    },
                    onNextButtonClicked = {navController.navigate(route = LunchTrayScreen.Accompaniment.name)},
                    onSelectionChanged = { item ->
                        viewModel.updateSideDish(item)
                    },
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )

            }
            composable(route = LunchTrayScreen.Accompaniment.name) {
                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(route = LunchTrayScreen.Start.name, inclusive = false)
                    },
                    onNextButtonClicked = { navController.navigate(route = LunchTrayScreen.Checkout.name) },
                    onSelectionChanged = { item ->
                        viewModel.updateAccompaniment(item)
                    },
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }
            composable(route = LunchTrayScreen.Checkout.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onNextButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
                    },
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
                    },
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }

        }
    }
}


