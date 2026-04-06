package com.wbjang.football_api_practice.network


import com.wbjang.football_api_practice.model.MatchDetailResponse
import com.wbjang.football_api_practice.model.MatchesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FootballApiService {

    // 오늘 경기 목록
    @GET("matches")
    suspend fun getTodayMatches(): MatchesResponse

    // 경기 상세
    @GET("matches/{id}")
    @Headers(
        "X-Unfold-Lineups: true",
        "X-Unfold-Goals: true",
        "X-Unfold-Bookings: true"
    )
    suspend fun getMatchDetail(
        @Path("id") matchId: Int
    ): MatchDetailResponse
}