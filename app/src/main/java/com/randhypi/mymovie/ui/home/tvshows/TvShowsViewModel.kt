package com.randhypi.mymovie.ui.home.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.randhypi.mymovie.data.Movies
import com.randhypi.mymovie.utils.DummyTvShows
import com.randhypi.mymovie.data.TvShows
import com.randhypi.mymovie.data.source.MoviesRepository

class TvShowsViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    fun getTvShows(): LiveData<ArrayList<TvShows>>? = moviesRepository.getTvShows() as? LiveData<ArrayList<TvShows>>
}