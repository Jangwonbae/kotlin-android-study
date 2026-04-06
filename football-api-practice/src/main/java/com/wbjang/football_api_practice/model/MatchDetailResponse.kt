package com.wbjang.football_api_practice.model

import com.google.gson.annotations.SerializedName

data class MatchDetailResponse(
    val id: Int,
    val utcDate: String,
    val status: String,
    val matchday: Int?,
    val competition: Competition?,
    val homeTeam: MatchTeam,
    val awayTeam: MatchTeam,
    val score: Score,
    val goals: List<Goal>?,
    val bookings: List<Booking>?,
    val substitutions: List<Substitution>?
)

data class MatchTeam(
    val id: Int,
    val name: String,
    val shortName: String?,
    val crest: String?,
    val formation: String?,
    val lineup: List<Player>?,
    val bench: List<Player>?,
    val statistics: MatchStatistics?,
    val coach: Coach?
)

data class Player(
    val id: Int,
    val name: String,
    val position: String?,
    val shirtNumber: Int?
)

data class Coach(
    val id: Int?,
    val name: String?,
    val nationality: String?
)

data class Goal(
    val minute: Int?,
    val type: String?,
    val team: Team?,
    val scorer: Player?,
    val assist: Player?
)

data class Booking(
    val minute: Int?,
    val team: Team?,
    val player: Player?,
    val card: String?
)

data class Substitution(
    val minute: Int?,
    val team: Team?,
    val playerOut: Player?,
    val playerIn: Player?
)

data class MatchStatistics(
    @SerializedName("corner_kicks") val cornerKicks: Int?,
    @SerializedName("free_kicks") val freeKicks: Int?,
    @SerializedName("ball_possession") val ballPossession: Int?,
    @SerializedName("shots") val shots: Int?,
    @SerializedName("shots_on_goal") val shotsOnGoal: Int?,
    @SerializedName("yellow_cards") val yellowCards: Int?,
    @SerializedName("red_cards") val redCards: Int?,
    @SerializedName("fouls") val fouls: Int?,
    @SerializedName("offsides") val offsides: Int?
)