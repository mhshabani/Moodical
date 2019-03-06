package com.moodical.app.app

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.moodical.app.local.AppDatabase
import com.moodical.app.local.dao.UserDao
import com.moodical.app.local.datasource.LocalUserDataSource
import com.moodical.app.logic.repository.SongRepository
import com.moodical.app.logic.repository.UserRepository
import com.moodical.app.player.MediaDataManager
import com.moodical.app.player.MediaPlaybackManager
import com.moodical.app.remote.NetworkManager
import com.moodical.app.remote.datasource.SongsDataSource
import com.moodical.app.remote.datasource.UserDataSource
import com.moodical.app.remote.service.SongsService
import com.moodical.app.remote.service.UserService

class Injector {
    companion object {
        var authToken: String = ""
        var myUsername: String = ""

        var mediaPlaybackManager = MediaPlaybackManager(MediaDataManager())

        fun provideMediaPlaybackManager(): MediaPlaybackManager {
            return mediaPlaybackManager
        }

        fun provideSongRepository(): SongRepository {
            return SongRepository(provideSongDataSource())
        }

        fun provideSongDataSource(): SongsDataSource {
            return SongsDataSource(provideSongService())
        }

        fun provideSongService(): SongsService {
            return SongsService(provideNetworkManager())
        }

        fun provideUserRepository(context: Context): UserRepository {
            return UserRepository(provideUserDataSource(), provideLocalDataSource(context))
        }

        fun provideUserDataSource(): UserDataSource {
            return UserDataSource(provideUserService())
        }

        fun provideUserService(): UserService {
            return UserService(provideNetworkManager())
        }

        fun provideNetworkManager(): NetworkManager {
            return NetworkManager()
        }

        fun provideLocalDataSource(context: Context): LocalUserDataSource {
            return LocalUserDataSource(provideUserDao(context))
        }

        fun provideUserDao(context: Context): UserDao {
            return provideAppDatabase(context).userDao()
        }

        private fun provideAppDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "db.data").build()
        }

        private fun provideDefaultSharedPref(context: Context): SharedPreferences {
            return context.getSharedPreferences("user.data", Context.MODE_PRIVATE)
        }
    }
}