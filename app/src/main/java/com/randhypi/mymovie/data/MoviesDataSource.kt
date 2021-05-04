package com.randhypi.mymovie.data

import androidx.lifecycle.LiveData
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity
import com.randhypi.mymovie.vo.Resource

interface MoviesDataSource {
    fun getMovies(): LiveData<Resource<List<MoviesEntity>>>
    fun getTvShows(): LiveData<Resource<List<TvShowsEntity>>>
    fun getDetailMovies(id: String): LiveData<MoviesEntity>
    fun getDetailTvShows(id: String): LiveData<TvShowsEntity>
    fun getFavMovies(): LiveData<List<MoviesEntity>>
    fun getFavTvShows():LiveData<List<TvShowsEntity>>
    fun setFavMovie(movie: MoviesEntity)
    fun setFavTvShow(tv: TvShowsEntity)
}