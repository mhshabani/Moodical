package com.moodical.app.remote.datasource

import com.moodical.app.remote.model.request.LoginRequest
import com.moodical.app.remote.model.request.RegisterRequest
import com.moodical.app.remote.model.response.LoginResponse
import com.moodical.app.remote.model.response.RegisterResponse
import com.moodical.app.remote.service.UserService

class UserDataSource(private val userService: UserService) {
    fun login(username: String, password: String): LoginResponse {
        return userService.login(LoginRequest(username, password))
    }

    fun register(
        displayName: String,
        username: String,
        email: String,
        password: String
    ): RegisterResponse {
        return userService.register(
                RegisterRequest(
                    displayName = displayName,
                    username = username,
                    email = email,
                    password = password
                )
            )

    }
}