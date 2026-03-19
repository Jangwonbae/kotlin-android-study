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
package com.wbjang.data_persistence_room_codelab_practice.ui

import androidx.compose.ui.geometry.isEmpty
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.wbjang.data_persistence_room_codelab_practice.BusScheduleApplication
import com.wbjang.data_persistence_room_codelab_practice.data.BusSchedule
import com.wbjang.data_persistence_room_codelab_practice.data.BusSchedulesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlin.collections.emptyList

class BusScheduleViewModel(private val busSchedulesRepository: BusSchedulesRepository): ViewModel() {
    // 1. 상태를 변화시킬 "트리거"가 되는 변수를 만듭니다. (MutableStateFlow)
    private val _stopName = MutableStateFlow("")

    // 2. 위 트리거(_stopName)가 바뀔 때마다 자동으로 새로운 데이터를 가져오는 StateFlow를 만듭니다.
    @OptIn(ExperimentalCoroutinesApi::class)
    val routeSchedule: StateFlow<List<BusSchedule>> = _stopName
        .flatMapLatest { stopName ->
            if (stopName.isEmpty()) {
                flowOf(emptyList()) // 이름이 없으면 빈 리스트 반환
            } else {
                busSchedulesRepository.getBusScheduleFor(stopName) // 이름이 있으면 DB 조회
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // 3. UI에서 정류장을 선택했을 때 호출할 함수
    fun updateStopName(name: String) {
        _stopName.value = name
    }
    val fullSchedule : StateFlow<List<BusSchedule>> = busSchedulesRepository.getAllBusSchedules()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )


    // Get example bus schedule by stop
    fun getScheduleFor(stopName: String): Flow<List<BusSchedule>> = busSchedulesRepository.getBusScheduleFor(stopName)

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                var application = this[AndroidViewModelFactory.APPLICATION_KEY] as BusScheduleApplication
                BusScheduleViewModel(application.container.busSchedulesRepository)
            }
        }
    }
}
