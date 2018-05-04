package com.example.dmclaughlin.fortnitetracker.vo


data class StatsVO(
        val label: String,
        val field: String,
        val category: String,
        val valueDec: Double?,
        val valueInt: Int?,
        val value: String,
        val displayValue: String,
        val percentile: Double?
        )