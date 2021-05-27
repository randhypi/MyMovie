package com.capstone.core.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.capstone.core.data.source.local.entity.MoviesEntity
import com.capstone.core.data.source.local.entity.TvShowsEntity
import com.capstone.core.data.source.local.room.MoviesDao

class LocalDataSource(private val mMoviesDao: MoviesDao){

    fun getAllMovies(): DataSource.Factory<Int, MoviesEntity> = mMoviesDao.getMovies()
    fun getAllTv(): DataSource.Factory<Int, TvShowsEntity> = mMoviesDao.getTvShows()
    fun getDetailMovie(moviesId: String): LiveData<MoviesEntity> = mMoviesDao.getDetailMovies(moviesId)
    fun getDetailTv(tvId: String): LiveData<TvShowsEntity> = mMoviesDao.getDetailTvShow(tvId)
    fun insertMovies(movies: List<MoviesEntity>) = mMoviesDao.insertMovies(movies)
    fun insertTvs(tvShows: List<TvShowsEntity>) = mMoviesDao.insertTvShows(tvShows)
    fun updateMovie(movie: MoviesEntity) = mMoviesDao.updateMovie(movie)
    fun updateTv(tv: TvShowsEntity) = mMoviesDao.updateTv(tv)
    fun getFavMovie(): DataSource.Factory<Int, MoviesEntity> = mMoviesDao.getFavMovie()
    fun getFavTv():  DataSource.Factory<Int, TvShowsEntity> = mMoviesDao.getFavTvShow()

}