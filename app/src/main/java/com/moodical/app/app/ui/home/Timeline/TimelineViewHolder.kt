package com.moodical.app.app.ui.home.Timeline

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moodical.app.R
import com.moodical.app.logic.model.EventEntity
import com.squareup.picasso.Picasso

class TimelineViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
    fun bind(eventEntity: EventEntity) {
        mView.findViewById<TextView>(R.id.timeline_itme_event_owner).text = eventEntity.owner.username
        mView.findViewById<TextView>(R.id.timeline_item_song_artist).text = eventEntity.items.first().artist
        mView.findViewById<TextView>(R.id.timeline_item_remaining_time).text = eventEntity.estimatedRemaining
        val image = mView.findViewById<ImageView>(R.id.timeline_item_album_art)

        Picasso.get().load(eventEntity.owner.profile_pic).into(image)
//
//        mView.setOnClickListener {
//            Injector.provideMediaPlaybackManager().clearQueue().addToQueue(song)
//            playMediaFunc()
//        }
    }
}