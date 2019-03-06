package com.moodical.app.player

import com.moodical.app.logic.model.SongEntity

class MediaPlaybackManager(val mediaDataManager: MediaDataManager) {
    val playingNowList: MutableList<SongEntity> = mutableListOf()
    var currentMusicIndex : Int = -1

    fun addToQueue(songEntity: SongEntity): MediaPlaybackManager{
        playingNowList.add(songEntity)
        return this
    }

    fun clearQueue(): MediaPlaybackManager {
        playingNowList.clear()
        currentMusicIndex = -1
        return this
    }

    fun removeFromQueue(position: Int) {
        playingNowList.removeAt(position)
    }

    fun getNextMediaAddress(): String? {
        currentMusicIndex += 1
        return mediaDataManager.getMediaAddress(playingNowList[currentMusicIndex])
    }
}