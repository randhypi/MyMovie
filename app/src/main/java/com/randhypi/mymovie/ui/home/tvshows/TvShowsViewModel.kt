package com.randhypi.mymovie.ui.home.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.capstone.core.data.Resource
import com.capstone.core.domain.model.TvShows
import com.capstone.core.domain.usecase.MoviesUseCase




class TvShowsViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    fun getTvShows() = LiveDataReactiveStreams.fromPublisher(moviesUseCase.getTvShows())
}
