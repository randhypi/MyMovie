package com.randhypi.mymovie.ui.home


import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.randhypi.mymovie.R
import com.randhypi.mymovie.ui.home.movies.MoviesListFragment

import com.randhypi.mymovie.ui.home.tvshows.TvShowsListFragment

class SectionsPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(
    activity
){
companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.tab_text_1, R.string.tab_text_2)
    }
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 ->fragment = MoviesListFragment()
            1 ->fragment = TvShowsListFragment()
        }
        return fragment as Fragment
    }


}