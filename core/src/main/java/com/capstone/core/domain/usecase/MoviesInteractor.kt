package com.capstone.core.domain.usecase

import android.util.Log
import com.capstone.core.data.Resource
import com.capstone.core.domain.model.Movies
import com.capstone.core.domain.model.TvShows
import com.capstone.core.domain.repository.IMoviesRepository
import io.reactivex.Flowable

class MoviesInteractor(private val moviesRepository: IMoviesRepository) : MoviesUseCase {
    override fun getMovies(): Flowable<Resource<List<Movies>>> =
        moviesRepository.getMovies()


    override fun getTvShows()=
        moviesRepository.getTvShows()

    override fun getDetailMovies(id: String)=
        moviesRepository.getDetailMovies(id)

    override fun getDetailTvShows(id: String) =
        moviesRepository.getDetailTvShows(id)

    override fun getFavMovies()=
        moviesRepository.getFavMovies()

    override fun getFavTvShows() =
        moviesRepository.getFavTvShows()

    override fun setFavMovie(movie: Movies) =
        moviesRepository.setFavMovie(movie)

    override fun setFavTvShow(tv: TvShows) {
        Log.d("Movies Interactor",tv.toString())
        moviesRepository.setFavTvShow(tv)

    }
}