package com.moodical.app.remote.model.response

data class Music(
    var name: String?,
    var download_link: String?,
    var artwork: String?,
    var duration: Int?,
    var artist: Artist?,
    var genre: Genre?
)

data class Artist(
    var id: Int?,
    var name: String?
)

data class Genre(
    var id: Int?,
    var name: String?
)