package com.capstone.core.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.capstone.core.data.source.local.entity.MoviesEntity
import com.capstone.core.data.source.local.entity.TvShowsEntity
import com.capstone.core.data.source.local.room.MoviesDao
import io.reactivex.Flowable

class LocalDataSource(private val mMoviesDao: MoviesDao){

    fun getAllMovies(): Flowable<List<MoviesEntity>> = mMoviesDao.getMovies()
    fun getAllTv(): Flowable<List<TvShowsEntity>> = mMoviesDao.getTvShows()
    fun getDetailMovie(moviesId: String): Flowable<MoviesEntity> = mMoviesDao.getDetailMovies(moviesId)
    fun getDetailTv(tvId: String): Flowable<TvShowsEntity> = mMoviesDao.getDetailTvShow(tvId)
    fun insertMovies(movies: List<MoviesEntity>) = mMoviesDao.insertMovies(movies)
    fun insertTvs(tvShows: List<TvShowsEntity>) = mMoviesDao.insertTvShows(tvShows)
    fun updateMovie(movie: MoviesEntity) = mMoviesDao.updateMovie(movie)
    fun updateTv(tv: TvShowsEntity) = mMoviesDao.updateTv(tv)
    fun getFavMovie(): Flowable<List<MoviesEntity>> = mMoviesDao.getFavMovie()
    fun getFavTv():  Flowable<List<TvShowsEntity>> = mMoviesDao.getFavTvShow()

}