package com.moodical.app.logic.repository

import com.moodical.app.app.Injector
import com.moodical.app.local.datasource.LocalUserDataSource
import com.moodical.app.local.model.LocalUser
import com.moodical.app.remote.datasource.UserDataSource

class UserRepository(private val userDataSource: UserDataSource, private val localUserDataSource: LocalUserDataSource) {
    fun login(username: String, password: String, onLoginResult: (String) -> Unit) {
        Thread {
            val response = userDataSource.login(username, password)

            if (response.token.isNullOrEmpty()) {
                if (response.message.isNullOrEmpty()) {
                    onLoginResult("Error in communicating with server.")
                } else {
                    onLoginResult(response.message.toString())
                }
            } else {
                Injector.authToken = response.token.toString()
                Injector.myUsername = username
                onLoginResult("")
            }
        }.start()
    }

    fun register(
        displayName: String,
        username: String,
        email: String,
        password: String,
        onRegisterResult: (String) -> Unit
    ) {
        Thread {
            val response = userDataSource.register(displayName, username, email, password)
            if (response.token.isNullOrEmpty()) {
                if (response.message.isNullOrEmpty()) {
                    onRegisterResult("Error in communicating with server.")
                } else {
                    onRegisterResult(response.message.toString())
                }
            } else {
                Injector.authToken = response.token.toString()
                Injector.myUsername = username
                localUserDataSource.addNewUser(
                    LocalUser(
                        id = null,
                        displayName = displayName,
                        username = username,
                        email = email,
                        pictureUrl = null
                    )
                )
                onRegisterResult("")
            }
        }.start()
    }
}