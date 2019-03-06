package com.moodical.app.logic.mapper

import com.moodical.app.logic.model.EventEntity
import com.moodical.app.logic.model.OwnerEntity
import com.moodical.app.logic.model.SongEntity
import com.moodical.app.logic.model.TrackInfoEntity
import com.moodical.app.remote.model.response.Event
import com.moodical.app.remote.model.response.Music

fun Music.toSongEntity() = SongEntity(
    name = name,
    artist = artist?.name,
    url = download_link,
    artworkUrl = artwork
)

fun Event.toEventEntity(): EventEntity {
    items.sortedBy {
        it.order
    }

    return EventEntity(
        owner = OwnerEntity(owner.username ?: "Unknown", owner.display_name ?: "Unknown", owner.profile_pic ?: ""),
        items = items.map {
            it.music.toSongEntity()
        },
        membersCount = meta_info.members_count ?: 0,
        estimatedRemaining = meta_info.remaining_time ?: "Unknown",
        currentTrackInfo = TrackInfoEntity(
            offset = meta_info.current_track_info.offset ?: 0,
            track_time = meta_info.current_track_info.track_time ?: 0
        )
    )
}