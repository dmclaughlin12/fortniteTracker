package com.example.dmclaughlin.fortnitetracker.vo

import com.squareup.moshi.Json


data class CategoryStatsVO(
        val trnRating: StatsVO,
        val score: StatsVO,
        @Json(name = "top1") val topOne: StatsVO,
        @Json(name = "top3") val topThree: StatsVO,
        @Json(name = "top10") val topTen: StatsVO,
        @Json(name = "kd") val killDeathRatio: StatsVO
)