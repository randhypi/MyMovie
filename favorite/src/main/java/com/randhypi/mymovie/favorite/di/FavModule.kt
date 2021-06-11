package com.randhypi.mymovie.favorite.di

import com.randhypi.mymovie.favorite.movies.MoviesFavViewModel
import com.randhypi.mymovie.favorite.tvshows.TvShowsFavViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module(override = true) {
    viewModel { MoviesFavViewModel(get()) }
    viewModel { TvShowsFavViewModel(get()) }
}