package com.randhypi.mymovie.favorite.tvshows


import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.capstone.core.domain.usecase.MoviesUseCase

class TvShowsFavViewModel(private val moviesUseCase: MoviesUseCase): ViewModel() {
    fun getTvShowsFav() = LiveDataReactiveStreams.fromPublisher(moviesUseCase.getFavTvShows())
}