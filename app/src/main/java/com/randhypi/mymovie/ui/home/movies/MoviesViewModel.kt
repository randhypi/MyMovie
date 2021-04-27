package com.randhypi.mymovie.ui.home.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.randhypi.mymovie.utils.DummyMovies
import com.randhypi.mymovie.data.Movies
import com.randhypi.mymovie.data.api.ApiConfig
import com.randhypi.mymovie.data.api.ApiServices
import com.randhypi.mymovie.data.source.MoviesRepository
import com.randhypi.mymovie.data.source.remote.response.RemoteDataSource
import com.randhypi.mymovie.data.source.response.ResponseMovies
import com.randhypi.mymovie.data.source.response.ResultsItem
import com.randhypi.mymovie.ui.home.movies.MoviesListFragment.Companion.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    fun getMovies(): LiveData<List<Movies>> = moviesRepository.getMovies()
}

