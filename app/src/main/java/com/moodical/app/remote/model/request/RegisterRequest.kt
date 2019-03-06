package com.moodical.app.remote.model.request

data class RegisterRequest(
    val displayName: String,
    val username: String,
    val email: String,
    val password: String
)