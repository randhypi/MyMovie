package com.randhypi.mymovie.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity
import com.randhypi.mymovie.vo.Resource

interface MoviesDataSource {
    fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>>
    fun getTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>>
    fun getDetailMovies(id: String): LiveData<MoviesEntity>
    fun getDetailTvShows(id: String): LiveData<TvShowsEntity>
    fun getFavMovies(): LiveData<PagedList<MoviesEntity>>
    fun getFavTvShows():LiveData<PagedList<TvShowsEntity>>
    fun setFavMovie(movie: MoviesEntity)
    fun setFavTvShow(tv: TvShowsEntity)
}