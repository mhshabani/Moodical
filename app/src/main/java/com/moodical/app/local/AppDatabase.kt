package com.moodical.app.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moodical.app.local.dao.UserDao
import com.moodical.app.local.model.LocalUser

@Database(entities = [LocalUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}