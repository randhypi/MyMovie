package com.randhypi.mymovie.ui.home.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity
import com.randhypi.mymovie.data.MoviesRepository
import com.randhypi.mymovie.vo.Resource

class TvShowsViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    fun getTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>> = moviesRepository.getTvShows()
}