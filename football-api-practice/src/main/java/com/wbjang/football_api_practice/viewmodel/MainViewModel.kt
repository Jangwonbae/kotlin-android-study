package com.wbjang.football_api_practice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wbjang.football_api_practice.model.Match
import com.wbjang.football_api_practice.model.MatchDetailResponse
import com.wbjang.football_api_practice.network.FootballApiService
import com.wbjang.football_api_practice.repository.MatchRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 축구 경기 데이터를 관리하고 UI 상태를 제공하는 ViewModel 클래스입니다.
 * [FootballApiService]를 통해 데이터를 가져오며, 매치 목록 및 상세 정보를 [StateFlow]로 노출합니다.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val matchRepository: MatchRepository
) : ViewModel() {

    // 오늘 경기 목록
    private val _matches = MutableStateFlow<List<Match>>(emptyList())
    /**
     * 현재 로드된 오늘 경기 목록을 나타내는 [StateFlow]입니다.
     */
    val matches: StateFlow<List<Match>> = _matches.asStateFlow()

    // 경기 상세
    private val _matchDetail = MutableStateFlow<MatchDetailResponse?>(null)
    /**
     * 특정 경기의 상세 정보를 나타내는 [StateFlow]입니다. 선택된 경기가 없을 경우 null입니다.
     */
    val matchDetail: StateFlow<MatchDetailResponse?> = _matchDetail.asStateFlow()

    // 로딩 상태
    private val _isLoading = MutableStateFlow(false)
    /**
     * 데이터 로딩 중인지 여부를 나타내는 [StateFlow]입니다.
     */
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // 에러 메시지
    private val _error = MutableStateFlow<String?>(null)
    /**
     * 데이터 로딩 중 발생한 에러 메시지를 나타내는 [StateFlow]입니다. 에러가 없을 경우 null입니다.
     */
    val error: StateFlow<String?> = _error.asStateFlow()

    /**
     * API를 통해 오늘의 경기 목록을 불러옵니다.
     * 결과는 [matches]를 통해 업데이트됩니다.
     */
    fun loadTodayMatches() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = matchRepository.getTodayMatches()
                _matches.value = response.matches
            } catch (e: Exception) {
                _error.value = "에러: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * 특정 경기 ID를 사용하여 경기의 상세 정보를 불러옵니다.
     * 결과는 [matchDetail]을 통해 업데이트됩니다.
     *
     * @param matchId 조회할 경기의 고유 ID
     */
    fun loadMatchDetail(matchId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = matchRepository.getMatchDetail(matchId)
                _matchDetail.value = response
            } catch (e: Exception) {
                _error.value = "에러: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * 상세 정보를 초기화합니다. 화면 이동 시 이전 데이터를 지우기 위해 사용합니다.
     */
    fun clearMatchDetail() {
        _matchDetail.value = null
    }
}
