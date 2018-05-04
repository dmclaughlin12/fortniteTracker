package com.example.dmclaughlin.fortnitetracker.dao

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "user_stats")
data class UserEntity(
        @PrimaryKey
        @ColumnInfo(name = "username")
        var username: String,
        var platform: String,
        var jsonStats: String
) {
    constructor() : this("", "", "")
}
