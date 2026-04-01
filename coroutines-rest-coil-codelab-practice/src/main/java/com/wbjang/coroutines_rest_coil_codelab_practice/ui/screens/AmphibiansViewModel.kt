package com.wbjang.coroutines_rest_coil_codelab_practice.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wbjang.coroutines_rest_coil_codelab_practice.data.AmphibiansRepository
import com.wbjang.coroutines_rest_coil_codelab_practice.network.Amphibian
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<Amphibian>) : AmphibiansUiState
    object Error : AmphibiansUiState
    object Loading : AmphibiansUiState
}

@HiltViewModel
class AmphibiansViewModel @Inject constructor(
    private val amphibiansRepository: AmphibiansRepository
) : ViewModel() {
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)

    init {
        getAmphibians()
    }

    fun getAmphibians() {
        viewModelScope.launch {
            amphibiansUiState = AmphibiansUiState.Loading
            amphibiansUiState = try {
                AmphibiansUiState.Success(
                    amphibiansRepository.getAmphibians()
                )
            } catch (e: IOException) {
                AmphibiansUiState.Error
            } catch (e: Exception) {
                AmphibiansUiState.Error
            }
        }
    }
}
