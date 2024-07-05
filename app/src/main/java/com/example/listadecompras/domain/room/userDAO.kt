package com.example.listadecompras.domain.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT uid FROM user LIMIT 1")
    fun getAll(): LiveData<User>

    @Query("SELECT Count(uid) FROM user")
    fun count(): LiveData<Int>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Insert
    fun insert(vararg user: User)

    @Delete
    fun delete(user: User)
}