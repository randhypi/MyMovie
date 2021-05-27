package com.randhypi.mymovie.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.capstone.core.data.source.local.entity.MoviesEntity
import com.capstone.core.data.source.local.entity.TvShowsEntity
import com.capstone.core.data.MoviesRepository
import com.capstone.core.domain.model.Movies
import com.capstone.core.domain.model.TvShows
import com.capstone.core.domain.usecase.MoviesUseCase
import com.randhypi.mymovie.ui.detail.DetailFragment.Companion.TAG

class DetailViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {

    var id = MutableLiveData<String>()
    val moviesFav = MutableLiveData<Boolean>()
    val tvFav = MutableLiveData<Boolean>()

    fun setIdAndType(id: String?) {
        if (id != null) {
            this.id.value = id
        }
    }


    fun getDetailMovies(): LiveData<Movies> = Transformations.switchMap(id) {
        moviesUseCase.getDetailMovies(it)
    }

    fun getDetailTvSHows(): LiveData<TvShows> = Transformations.switchMap(id) {
        moviesUseCase.getDetailTvShows(it)
    }

    fun setFavMovie(detailMovies: Movies?) {
        detailMovies?.let {
            it.favorite = !it.favorite!!
        }

        if (detailMovies != null) {
            moviesUseCase.setFavMovie(detailMovies)
            moviesFav.value = detailMovies.favorite?.let { it }
        }
    }

    fun setFavTvShow(detailTvShow: TvShows?) {
        detailTvShow?.let {
            it.favorite = !it.favorite!!
        }

        if (detailTvShow != null) {
            moviesUseCase.setFavTvShow(detailTvShow)
            tvFav.value = detailTvShow.favorite?.let { it }
        }
    }

    fun getMovieFav(): LiveData<Boolean> {
        return moviesFav
    }

    fun getTvFav():LiveData<Boolean>{
        return tvFav
    }


}