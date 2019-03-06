package com.moodical.app.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class LocalUser(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val username: String,
    val displayName: String?,
    val email: String,
    val pictureUrl: String?
)