package com.randhypi.mymovie.ui.home.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.data.MoviesRepository

@Suppress("UNCHECKED_CAST")
class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    fun getMovies(): LiveData<List<MoviesEntity>> = moviesRepository.getMovies()
}

