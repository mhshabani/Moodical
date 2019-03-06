package com.moodical.app.app.ui.home.Songs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moodical.app.R
import com.moodical.app.logic.model.SongEntity


class SongsListAdapter(
    private val mValues: List<SongEntity>,
    private val playMediaFunc: () -> Unit
) : RecyclerView.Adapter<SongsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_songs_item, parent, false)
        return SongsViewHolder(playMediaFunc, view)
    }

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {
        val item = mValues[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = mValues.size
}
