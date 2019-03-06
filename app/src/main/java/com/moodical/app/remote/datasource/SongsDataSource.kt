package com.moodical.app.remote.datasource

import com.moodical.app.remote.model.response.Event
import com.moodical.app.remote.model.response.Music
import com.moodical.app.remote.service.SongsService

class SongsDataSource(private val songsService: SongsService) {
    fun getMusicList(): List<Music>? {
        return songsService.getMusicList()
    }

    fun getEventList(): List<Event>? {
        return songsService.getEventList()
    }
}