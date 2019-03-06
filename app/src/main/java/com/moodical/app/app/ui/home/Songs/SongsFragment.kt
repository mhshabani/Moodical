package com.moodical.app.app.ui.home.Songs

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moodical.app.R
import com.moodical.app.app.Injector
import com.moodical.app.app.MainActivity

class SongsFragment : Fragment() {
    private var columnCount = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_songs_list, container, false)

        Injector.provideSongRepository().getSongs { songs ->
            activity?.runOnUiThread {
                if (view is RecyclerView && songs != null) {
                    with(view) {
                        layoutManager = when {
                            columnCount <= 1 -> LinearLayoutManager(context)
                            else -> GridLayoutManager(context, columnCount)
                        }
                        adapter = SongsListAdapter(songs, ::playMedia)
                    }
                }
            }
        }
        // Set the adapter
        return view
    }

    private fun playMedia() {
        val broadcastIntent = Intent(MainActivity.Broadcast_PLAY_NEW_AUDIO)
        activity?.sendBroadcast(broadcastIntent)
    }
}
