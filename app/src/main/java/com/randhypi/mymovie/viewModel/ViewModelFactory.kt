package com.randhypi.mymovie.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.randhypi.mymovie.data.MoviesRepository
import com.randhypi.mymovie.di.Injection
import com.randhypi.mymovie.ui.detail.DetailViewModel
import com.randhypi.mymovie.ui.home.movies.MoviesFavViewModel
import com.randhypi.mymovie.ui.home.movies.MoviesViewModel
import com.randhypi.mymovie.ui.home.tvshows.TvShowsFavViewModel
import com.randhypi.mymovie.ui.home.tvshows.TvShowsViewModel

class ViewModelFactory private constructor(private val mMoviesRepository: MoviesRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MoviesFavViewModel::class.java) -> {
                return MoviesFavViewModel(mMoviesRepository) as T
            }
            modelClass.isAssignableFrom(TvShowsFavViewModel::class.java) -> {
                return TvShowsFavViewModel(mMoviesRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(mMoviesRepository) as T
            }
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                return MoviesViewModel(mMoviesRepository) as T
            }
            modelClass.isAssignableFrom(TvShowsViewModel::class.java) -> {
                return TvShowsViewModel(mMoviesRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}