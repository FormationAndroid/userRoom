package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
}

private var INSTANCE: AppDatabase? = null

fun getDatabase(context: Context): AppDatabase? {
    if (INSTANCE == null){
        INSTANCE = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "MyDatabase")
            .build()
    }
    return INSTANCE
}