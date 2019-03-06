package com.moodical.app.app.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.moodical.app.R
import com.moodical.app.app.ui.home.Songs.SongsFragment
import com.moodical.app.app.ui.home.Timeline.TimelineFragment
import com.moodical.app.player.MediaPlaybackManager


class HomeBaseFragment : Fragment(), ViewPager.OnPageChangeListener {
    private var currentItem: MenuItem? = null
    private var bottomNavigationView : BottomNavigationView? = null
    private var mediaPlaybackManager: MediaPlaybackManager? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomNavigationView = view.findViewById(R.id.navigation)

        val viewPager = view.findViewById<ViewPager>(R.id.viewpager)

        bottomNavigationView?.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_timeline -> viewPager.currentItem = 0
                R.id.navigation_browse -> viewPager.currentItem = 1
                R.id.navigation_profile -> viewPager.currentItem = 2
                else -> viewPager.currentItem = 0
            }

            true
        }

        viewPager.addOnPageChangeListener(this)

        setupViewPager(viewPager)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_fragment, container, false)
    }


    private fun setupViewPager(viewPager: ViewPager) {
        val res = context?.resources ?: return
        val adapter = Adapter(childFragmentManager).apply {
            addFragment(
                fragment = TimelineFragment(),
                title = res.getString(R.string.timeline)
            )
            addFragment(
                fragment = SongsFragment(),
                title = res.getString(R.string.songs)
            )
        }
        viewPager.adapter = adapter
        viewPager.setCurrentItem(0, false)
        currentItem = bottomNavigationView?.menu?.getItem(0)
    }


    internal class Adapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val fragments = ArrayList<Fragment>()
        private val titles = ArrayList<String>()

        fun addFragment(fragment: Fragment, title: String) {
            fragments.add(fragment)
            titles.add(title)
        }

        override fun getItem(position: Int) = fragments[position]

        override fun getCount() = fragments.size

        override fun getPageTitle(position: Int) = titles[position]
    }

    private fun activatePage(position: Int) {
        if (currentItem != null) {
            currentItem!!.isChecked = false
        } else {
            bottomNavigationView?.menu?.getItem(0)?.isChecked = false
        }
        Log.d("page", "onPageSelected: $position")
        currentItem = bottomNavigationView?.menu?.getItem(position)
        currentItem?.isChecked = true
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        activatePage(position)
    }

    override fun onPageSelected(position: Int) {
        activatePage(position)
    }
}
