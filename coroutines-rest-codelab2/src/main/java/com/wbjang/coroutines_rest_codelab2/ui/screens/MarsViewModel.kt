package com.wbjang.coroutines_rest_codelab2.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wbjang.coroutines_rest_codelab2.network.MarsApi
import kotlinx.coroutines.launch
import kotlinx.serialization.InternalSerializationApi
import java.io.IOException

sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}
class MarsViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
//    API에 사용된 URL 또는 URI가 잘못됨
//    서버를 사용할 수 없어 앱을 서버에 연결할 수 없음
//    네트워크 지연 문제가 있음
//    기기의 인터넷 연결이 불안정하거나 기기가 인터넷에 연결되지 않음
    @OptIn(InternalSerializationApi::class)
    fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = try {
                val listResult = MarsApi.retrofitService.getPhotos()
                MarsUiState.Success(
                    "Success: ${listResult.size} Mars photos retrieved"
                )
            } catch (e: IOException) {
                MarsUiState.Error
            }
        }
    }
}