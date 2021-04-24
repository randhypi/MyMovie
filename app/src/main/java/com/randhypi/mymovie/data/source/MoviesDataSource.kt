package com.randhypi.mymovie.data.source

import androidx.lifecycle.LiveData
import com.randhypi.mymovie.data.Movies
import com.randhypi.mymovie.data.TvShows

interface MoviesDataSource {
    fun getMovies(): LiveData<List<Movies>>
    fun getTvShows(): LiveData<List<TvShows>>
    fun getDetailMovies(id: String): LiveData<Movies>
    fun getDetailTvShows(id: String): LiveData<TvShows>

}