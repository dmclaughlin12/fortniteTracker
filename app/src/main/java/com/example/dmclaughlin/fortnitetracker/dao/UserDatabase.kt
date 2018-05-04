package com.example.dmclaughlin.fortnitetracker.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(UserEntity::class), version = 1)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
}