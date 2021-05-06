package com.randhypi.mymovie.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity
import com.randhypi.mymovie.data.MoviesRepository
import com.randhypi.mymovie.ui.detail.DetailFragment.Companion.TAG

class DetailViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    var id = MutableLiveData<String>()
    val moviesFav = MutableLiveData<Boolean>()
    val tvFav = MutableLiveData<Boolean>()

    fun setIdAndType(id: String?) {
        if (id != null) {
            this.id.value = id
        }
    }


    fun getDetailMovies(): LiveData<MoviesEntity> = Transformations.switchMap(id) {
        moviesRepository.getDetailMovies(it)
    }

    fun getDetailTvSHows(): LiveData<TvShowsEntity> = Transformations.switchMap(id) {
        moviesRepository.getDetailTvShows(it)
    }

    fun setFavMovie(detailMovies: MoviesEntity?) {
        detailMovies?.let {
            it.favorite = !it.favorite!!
        }


        if (detailMovies != null) {
            moviesRepository.setFavMovie(detailMovies)
            moviesFav.value = detailMovies.favorite
        }
    }

    fun setFavTvShow(detailTvShow: TvShowsEntity?) {
        detailTvShow?.let {
            it.favorite = !it.favorite!!
        }

        Log.d(TAG,detailTvShow.toString())
        if (detailTvShow != null) {
            moviesRepository.setFavTvShow(detailTvShow)
            tvFav.value = detailTvShow.favorite
        }
    }

    fun getMovieFav(): LiveData<Boolean> {
        return moviesFav
    }

    fun getTvFav():LiveData<Boolean>{
        return tvFav
    }


}