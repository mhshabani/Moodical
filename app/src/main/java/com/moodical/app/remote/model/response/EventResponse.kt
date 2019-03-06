package com.moodical.app.remote.model.response

data class Event(
    val owner: Owner,
    val items: List<EventItem>,
    val meta_info: MetaInfo,
    val date: String?,
    val is_private: Boolean,
    val played_at: String?
)

data class Owner(
    val username: String?,
    val display_name: String?,
    val profile_pic: String?
)

data class EventItem(
    val id: Int?,
    val order: Int?,
    val music: Music
)

data class MetaInfo(
    val members_count: Int?,
    val remaining_time: String?,
    val interaction_count: Int?,
    val current_track_info: TrackInfo
)

data class TrackInfo(
    val offset: Int?,
    val track_time: Int?
)