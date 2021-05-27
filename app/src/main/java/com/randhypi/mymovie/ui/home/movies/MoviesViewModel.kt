package com.randhypi.mymovie.ui.home.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.capstone.core.data.Resource
import com.capstone.core.domain.model.Movies
import com.capstone.core.domain.usecase.MoviesUseCase


class MoviesViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    fun getMovies(): LiveData<Resource<PagedList<Movies>>> = moviesUseCase.getMovies()
}

