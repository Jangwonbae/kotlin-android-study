package com.wbjang.coroutines_rest_coil_codelab_practice.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.wbjang.coroutines_rest_coil_codelab_practice.AmphibiansApplication
import com.wbjang.coroutines_rest_coil_codelab_practice.data.AmphibiansRepository
import com.wbjang.coroutines_rest_coil_codelab_practice.network.Amphibian
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<Amphibian>) : AmphibiansUiState
    object Error : AmphibiansUiState
    object Loading : AmphibiansUiState
}
class AmphibiansViewModel(private val amphibiansRepository: AmphibiansRepository) : ViewModel() {
    var amphibiansUiState : AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)

    init {
        getAmphibians()
    }


    fun getAmphibians() {
        viewModelScope.launch {
            amphibiansUiState = try {
                AmphibiansUiState.Success(
                    amphibiansRepository.getAmphibians()
                )
            } catch (e: IOException) {
                AmphibiansUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                val amphibiansRepository = application.container.amphibiansRepository
                AmphibiansViewModel(amphibiansRepository = amphibiansRepository)
            }
        }
    }

}