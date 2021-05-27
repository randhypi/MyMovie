package com.randhypi.mymovie.ui.home.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.capstone.core.data.source.local.entity.MoviesEntity
import com.capstone.core.data.MoviesRepository
import com.capstone.core.domain.model.Movies
import com.capstone.core.domain.usecase.MoviesUseCase


class MoviesFavViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    fun getMoviesFav(): LiveData<PagedList<Movies>> = moviesUseCase.getFavMovies()
}

