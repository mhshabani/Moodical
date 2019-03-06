package com.moodical.app.local.dao

import androidx.room.*
import com.moodical.app.local.model.LocalUser


@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun listAll(): List<LocalUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg localUser: LocalUser)

    @Update
    fun update(localUser: LocalUser)

    @Delete
    fun delete(vararg localUser: LocalUser)

    @Query("DELETE FROM user")
    fun clearAll()
}