package com.capstone.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.capstone.core.data.Resource
import com.capstone.core.domain.model.Movies
import com.capstone.core.domain.model.TvShows

interface MoviesUseCase {
    fun getMovies(): LiveData<Resource<PagedList<Movies>>>
    fun getTvShows(): LiveData<Resource<PagedList<TvShows>>>
    fun getDetailMovies(id: String): LiveData<Movies>
    fun getDetailTvShows(id: String): LiveData<TvShows>
    fun getFavMovies(): LiveData<PagedList<Movies>>
    fun getFavTvShows(): LiveData<PagedList<TvShows>>
    fun setFavMovie(movie: Movies)
    fun setFavTvShow(tv: TvShows)
}