package com.randhypi.mymovie.favorite.movies


import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.capstone.core.domain.usecase.MoviesUseCase


class MoviesFavViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    fun getMoviesFav() = LiveDataReactiveStreams.fromPublisher(moviesUseCase.getFavMovies())
}

