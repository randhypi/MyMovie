package com.randhypi.mymovie.ui.home.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.data.MoviesRepository
import com.randhypi.mymovie.vo.Resource

@Suppress("UNCHECKED_CAST")
class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    fun getMovies(): LiveData<Resource<List<MoviesEntity>>> = moviesRepository.getMovies()
}

