package com.moodical.app.remote.service

import com.google.gson.Gson
import com.moodical.app.app.Injector
import com.moodical.app.remote.NetworkManager
import com.moodical.app.remote.model.response.Music
import com.google.gson.reflect.TypeToken
import com.moodical.app.remote.model.response.Event


class SongsService(private val networkManager: NetworkManager) {
    fun getMusicList(): List<Music> {
        val rawData = networkManager.get(URL_SONGS, "Token " + Injector.authToken)
        val listType = object : TypeToken<List<Music>?>(){}.type
        return Gson().fromJson(rawData, listType)
    }

    fun getEventList(): List<Event> {
        val rawData = networkManager.get(URL_EVENTS, "Token " + Injector.authToken)
        val listType = object : TypeToken<List<Event>?>(){}.type
        return Gson().fromJson(rawData, listType)
    }

    companion object {
        const val URL_SONGS = "http://192.168.1.185:9099/music"
        const val URL_EVENTS = "http://192.168.1.185:9099/timeline"
    }
}