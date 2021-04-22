package com.randhypi.mymovie.ui.detail

import androidx.lifecycle.ViewModel
import com.randhypi.mymovie.data.movies.DummyMovies
import com.randhypi.mymovie.data.movies.Movies
import com.randhypi.mymovie.data.tvshow.DummyTvShows
import com.randhypi.mymovie.data.tvshow.TvShows

class DetailViewModel : ViewModel() {

    private lateinit var id: String

    fun setIdAndType(id: String){
        this.id = id
    }



    fun getDetailMovies(): Movies? {
        var dummy = DummyMovies.moviesDummy()

        val movies = dummy.find {
            it.id == id
        }

        return movies
    }

    fun getDetailTvSHows(): TvShows? {
        var dummy = DummyTvShows.tvShowsDummy()

        val tvShows = dummy.find {
            it.id == id
        }

        return tvShows

    }

}