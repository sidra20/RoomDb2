package com.sidrakotlin.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {

    @Insert
    fun insertUser(user:UserEntity)

    @Delete
    fun deleteUser(user: UserEntity)

    @Update
    fun updateUser(user: UserEntity)

    @Query("SELECT * FROM user")
    fun getAll():MutableList<UserEntity>
}