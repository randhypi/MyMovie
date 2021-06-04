package com.capstone.core.domain.usecase


import com.capstone.core.data.Resource
import com.capstone.core.domain.model.Movies
import com.capstone.core.domain.model.TvShows
import io.reactivex.Flowable

interface MoviesUseCase {
    fun getMovies(): Flowable<Resource<List<Movies>>>
    fun getTvShows(): Flowable<Resource<List<TvShows>>>
    fun getDetailMovies(id: String): Flowable<Movies>
    fun getDetailTvShows(id: String): Flowable<TvShows>
    fun getFavMovies(): Flowable<List<Movies>>
    fun getFavTvShows(): Flowable<List<TvShows>>
    fun setFavMovie(movie: Movies)
    fun setFavTvShow(tv: TvShows)
}