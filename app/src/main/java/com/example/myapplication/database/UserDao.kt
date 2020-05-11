package com.example.myapplication.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getUsers(): List<User>

    @Query("SELECT * FROM User WHERE id=:id")
    fun getUserById(id: Int): User

    @Insert
    fun insertUsers(vararg users: User)

    @Delete
    fun deleteUser(user: User)

}