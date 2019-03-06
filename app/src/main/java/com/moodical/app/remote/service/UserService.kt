package com.moodical.app.remote.service

import com.google.gson.Gson
import com.moodical.app.remote.NetworkManager
import com.moodical.app.remote.model.request.LoginRequest
import com.moodical.app.remote.model.request.RegisterRequest
import com.moodical.app.remote.model.response.LoginResponse
import com.moodical.app.remote.model.response.RegisterResponse

class UserService(private val networkManager: NetworkManager) {
    fun login(loginRequest: LoginRequest): LoginResponse {
        val requestBody = Gson().toJson(loginRequest)
        val rawData = networkManager.post(URL_LOGIN, requestBody)
        println(rawData)
        return Gson().fromJson(rawData, LoginResponse::class.java)
    }

    fun register(registerRequest: RegisterRequest): RegisterResponse {
        val requestBody = Gson().toJson(registerRequest)
        val rawData = networkManager.post(URL_REGISTER, requestBody)
        return Gson().fromJson(rawData, RegisterResponse::class.java)
    }

    companion object {
        const val URL_LOGIN = "http://192.168.1.185:9099/login"
        const val URL_REGISTER = "http://192.168.1.185:9099/register"
    }
}