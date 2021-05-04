package com.randhypi.mymovie.ui.home.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity
import com.randhypi.mymovie.data.MoviesRepository

class TvShowsViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    fun getTvShows(): LiveData<ArrayList<TvShowsEntity>>? = moviesRepository.getTvShows() as? LiveData<ArrayList<TvShowsEntity>>
}