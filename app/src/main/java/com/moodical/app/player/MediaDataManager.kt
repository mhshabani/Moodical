package com.moodical.app.player

import com.moodical.app.logic.model.SongEntity

class MediaDataManager {
    fun getMediaAddress(songEntity: SongEntity): String {
        return songEntity.url ?: ""
    }
}