package com.randhypi.mymovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity
import com.randhypi.mymovie.data.MoviesRepository

class DetailViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private lateinit var id: String

    fun setIdAndType(id: String?){
        if (id != null) {
            this.id = id
        }
    }



    fun getDetailMovies(): LiveData<MoviesEntity> = moviesRepository.getDetailMovies(id)

    fun getDetailTvSHows():LiveData<TvShowsEntity> = moviesRepository.getDetailTvShows(id)

}