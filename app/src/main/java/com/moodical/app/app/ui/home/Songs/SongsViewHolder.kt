package com.moodical.app.app.ui.home.Songs

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moodical.app.R
import com.moodical.app.app.Injector
import com.moodical.app.logic.model.SongEntity
import com.squareup.picasso.Picasso

class SongsViewHolder(
    private val playMediaFunc: () -> Unit,
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    fun bind(song: SongEntity) {
        itemView.findViewById<TextView>(R.id.song_title).text = song.name
        itemView.findViewById<TextView>(R.id.song_artist).text = song.artist
        val image = itemView.findViewById<ImageView>(R.id.albumArt)

        Picasso.get().load(song.artworkUrl).into(image)

        itemView.setOnClickListener {
            Injector.provideMediaPlaybackManager().clearQueue().addToQueue(song)
            playMediaFunc()
        }
    }
}