package com.example.dmclaughlin.fortnitetracker.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query



@Dao
interface UserDao {

    @Query("SELECT * FROM user_stats")
    fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM user_stats WHERE username LIKE :name")
    fun getUser(name: String): UserEntity

    @Insert
    fun insertUser(user: UserEntity)
}