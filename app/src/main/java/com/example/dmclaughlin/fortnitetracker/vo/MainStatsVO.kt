package com.example.dmclaughlin.fortnitetracker.vo

import com.example.dmclaughlin.fortnitetracker.vo.CategoryStatsVO
import com.squareup.moshi.Json

data class MainStatsVO(
        @Json(name = "p2") val soloStats: CategoryStatsVO,
        @Json(name = "p9") val doubleStats: CategoryStatsVO,
        @Json(name = "p10") val squadStats: CategoryStatsVO
)