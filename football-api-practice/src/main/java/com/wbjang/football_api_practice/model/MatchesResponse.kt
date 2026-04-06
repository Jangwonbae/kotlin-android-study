package com.wbjang.football_api_practice.model

data class MatchesResponse(
    val matches: List<Match>
)

data class Match(
    val id: Int,
    val utcDate: String,
    val status: String,
    val matchday: Int?,
    val competition: Competition?,
    val homeTeam: Team,
    val awayTeam: Team,
    val score: Score
)

data class Competition(
    val id: Int,
    val name: String,
    val emblem: String?
)

data class Team(
    val id: Int,
    val name: String,
    val shortName: String?,
    val crest: String?
)

data class Score(
    val winner: String?,
    val fullTime: ScoreDetail?,
    val halfTime: ScoreDetail?
)

data class ScoreDetail(
    val home: Int?,
    val away: Int?
)