package com.capstone.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bumptech.glide.load.engine.Resource
import com.capstone.core.data.MoviesRepository
import com.capstone.core.domain.model.Movies
import com.capstone.core.domain.model.TvShows
import com.capstone.core.domain.repository.IMoviesRepository

class MoviesInteractor(private val moviesRepository: IMoviesRepository): MoviesUseCase {
    override fun getMovies(): LiveData<Resource<PagedList<Movies>>> =
        moviesRepository.getMovies()


    override fun getTvShows(): LiveData<Resource<PagedList<TvShows>>>  =
        moviesRepository.getTvShows()

    override fun getDetailMovies(id: String): LiveData<Movies> =
        moviesRepository.getDetailMovies(id)

    override fun getDetailTvShows(id: String): LiveData<TvShows> =
        moviesRepository.getDetailTvShows(id)

    override fun getFavMovies(): LiveData<PagedList<Movies>> =
        moviesRepository.getFavMovies()

    override fun getFavTvShows(): LiveData<PagedList<TvShows>> =
        moviesRepository.getFavTvShows()

    override fun setFavMovie(movie: Movies) =
        moviesRepository.setFavMovie(movie)

    override fun setFavTvShow(tv: TvShows)  =
        moviesRepository.setFavTvShow(tv)
}