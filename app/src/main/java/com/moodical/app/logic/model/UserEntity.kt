package com.moodical.app.logic.model

data class UserEntity(
    val username: String,
    var displayName: String? = null,
    val email: String,
    var token: String? = null,
    val pictureUrl: String? = null
)