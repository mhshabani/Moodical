package com.moodical.app.logic.repository

import com.moodical.app.logic.mapper.toEventEntity
import com.moodical.app.logic.mapper.toSongEntity
import com.moodical.app.logic.model.EventEntity
import com.moodical.app.logic.model.SongEntity
import com.moodical.app.remote.datasource.SongsDataSource

class SongRepository(val songsDataSource: SongsDataSource) {
    fun getSongs(onSuccess: (List<SongEntity>?) -> Unit) {
        Thread {
            onSuccess(
                songsDataSource.getMusicList()?.map {
                    it.toSongEntity()
                }
            )
        }.start()
    }

    fun getEvents(onSuccess: (List<EventEntity>?) -> Unit) {
        Thread {
            onSuccess(
                songsDataSource.getEventList()?.map {
                    it.toEventEntity()
                }
            )
        }.start()
    }
}