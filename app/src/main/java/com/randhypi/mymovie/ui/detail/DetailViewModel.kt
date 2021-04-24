package com.randhypi.mymovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.randhypi.mymovie.utils.DummyMovies
import com.randhypi.mymovie.data.Movies
import com.randhypi.mymovie.utils.DummyTvShows
import com.randhypi.mymovie.data.TvShows
import com.randhypi.mymovie.data.source.MoviesRepository

class DetailViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private lateinit var id: String

    fun setIdAndType(id: String){
        this.id = id
    }



    fun getDetailMovies(): LiveData<Movies> = moviesRepository.getDetailMovies(id)

    fun getDetailTvSHows():LiveData<TvShows> = moviesRepository.getDetailTvShows(id)

}