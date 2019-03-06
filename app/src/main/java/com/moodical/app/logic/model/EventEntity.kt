package com.moodical.app.logic.model

data class EventEntity(
    val owner: OwnerEntity,
    val items: List<SongEntity>,
    val membersCount: Int = 0,
    val estimatedRemaining: String,
    val currentTrackInfo: TrackInfoEntity
)

data class OwnerEntity(
    val username: String,
    val display_name: String,
    val profile_pic: String
)


data class TrackInfoEntity(
    val offset: Int,
    val track_time: Int
)