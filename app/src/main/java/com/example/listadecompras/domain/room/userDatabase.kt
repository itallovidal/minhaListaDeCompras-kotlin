package com.example.listadecompras.domain.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    companion object{
        const val NAME = "user_db"
    }

    abstract fun userDao(): UserDao
}