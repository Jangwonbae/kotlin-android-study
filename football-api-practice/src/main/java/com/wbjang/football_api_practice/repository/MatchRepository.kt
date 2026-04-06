package com.wbjang.football_api_practice.repository

import com.wbjang.football_api_practice.model.MatchDetailResponse
import com.wbjang.football_api_practice.model.MatchesResponse
import com.wbjang.football_api_practice.network.FootballApiService
import jakarta.inject.Inject
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MatchRepository {

    suspend fun getTodayMatches(): MatchesResponse

    suspend fun getMatchDetail(matchId: Int ): MatchDetailResponse
}

class NetworkMatchRepository @Inject constructor(
    private val footballApiService : FootballApiService
): MatchRepository {
    override suspend fun getTodayMatches(): MatchesResponse {
        return footballApiService.getTodayMatches()
    }

    override suspend fun getMatchDetail(matchId: Int): MatchDetailResponse {
        return footballApiService.getMatchDetail(matchId)
    }
}