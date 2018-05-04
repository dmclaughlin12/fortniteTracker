package com.example.dmclaughlin.fortnitetracker.vo


data class FortniteStatsVO(
        val accountId: String,
        val platformName: String,
        val epicUserHandle: String,
        val stats: MainStatsVO
)