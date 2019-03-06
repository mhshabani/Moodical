package com.moodical.app.local.datasource

import com.moodical.app.local.dao.UserDao
import com.moodical.app.local.model.LocalUser

class LocalUserDataSource(val userDao: UserDao) {
    fun addNewUser(localUser: LocalUser) {
        userDao.insert(localUser)
    }
}