package com.randhypi.mymovie.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity
import com.randhypi.mymovie.data.source.local.room.MoviesDao

class LocalDataSource private constructor(private val mMoviesDao: MoviesDao){

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(moviesDao: MoviesDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(moviesDao)
    }


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