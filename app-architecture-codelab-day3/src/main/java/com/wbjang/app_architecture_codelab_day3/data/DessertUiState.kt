package com.wbjang.app_architecture_codelab_day3.data

import com.wbjang.app_architecture_codelab_day3.data.Datasource.dessertList
import kotlin.collections.get

data class DessertUiState(
    val currentDessertIndex : Int = 0,
    val dessertsSold: Int = 0,
    val revenue: Int = 0,
    val currentDessertPrice : Int = dessertList[currentDessertIndex].price,
    val currentDessertImageId : Int = dessertList[currentDessertIndex].imageId
)