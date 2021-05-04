package com.randhypi.mymovie.data.source.local

import androidx.lifecycle.LiveData
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity
import com.randhypi.mymovie.data.source.local.room.MoviesDao

class LocalDataSource private constructor(private val mMoviesDao: MoviesDao){

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(moviesDao: MoviesDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(moviesDao)
    }


    fun getAllMovies(): LiveData<List<MoviesEntity>> = mMoviesDao.getMovies()
    fun getAllTv(): LiveData<List<TvShowsEntity>> = mMoviesDao.getTvShows()
    fun getDetailMovie(moviesId: String): LiveData<MoviesEntity> = mMoviesDao.getDetailMovies(moviesId)
    fun getDetailTv(tvId: String): LiveData<TvShowsEntity> = mMoviesDao.getDetailTvShow(tvId)
    fun insertMovies(movies: List<MoviesEntity>) = mMoviesDao.insertMovies(movies)
    fun insertTvs(tvShows: List<TvShowsEntity>) = mMoviesDao.insertTvShows(tvShows)
    fun updateMovie(movie: MoviesEntity) = mMoviesDao.updateMovie(movie)
    fun updateTv(tv: TvShowsEntity) = mMoviesDao.updateTv(tv)
    fun getFavMovie() = mMoviesDao.getFavMovie()
    fun getFavTv() = mMoviesDao.getFavTvShow()

}