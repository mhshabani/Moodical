package com.moodical.app.app.ui.home.Timeline

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

class TimelineFragment : Fragment() {
    private var columnCount = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_timeline_list, container, false)

        Injector.provideSongRepository().getEvents { events ->
            // Set the adapter
            if (view is RecyclerView && events != null) {
                activity?.runOnUiThread {
                    with(view) {
                        layoutManager = when {
                            columnCount <= 1 -> LinearLayoutManager(context)
                            else -> GridLayoutManager(context, columnCount)
                        }
                        adapter = TimelineListAdapter(events.filter {
                            it.items.isNotEmpty()
                        })
                    }
                }
            }
        }
        return view
    }

}
