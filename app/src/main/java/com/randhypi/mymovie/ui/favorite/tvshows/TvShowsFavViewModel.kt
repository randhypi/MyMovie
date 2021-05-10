package com.randhypi.mymovie.ui.home.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity
import com.randhypi.mymovie.data.MoviesRepository

class TvShowsFavViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    fun getTvShowsFav(): LiveData<PagedList<TvShowsEntity>>? = moviesRepository.getFavTvShows()
}