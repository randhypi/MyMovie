package com.randhypi.mymovie.ui.home.tvshows

import androidx.lifecycle.ViewModel
import com.randhypi.mymovie.data.tvshow.DummyTvShows
import com.randhypi.mymovie.data.tvshow.TvShows

class TvShowsViewModel: ViewModel() {
    val getTvShows: ArrayList<TvShows> =  DummyTvShows.tvShowsDummy() as ArrayList<TvShows>
}