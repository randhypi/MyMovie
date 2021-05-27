package com.randhypi.mymovie.di

import com.capstone.core.domain.usecase.MoviesInteractor
import com.capstone.core.domain.usecase.MoviesUseCase
import com.randhypi.mymovie.ui.detail.DetailViewModel
import com.randhypi.mymovie.ui.home.movies.MoviesFavViewModel
import com.randhypi.mymovie.ui.home.movies.MoviesViewModel
import com.randhypi.mymovie.ui.home.tvshows.TvShowsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


    val useCaseModule = module {
        factory<MoviesUseCase> { MoviesInteractor(get()) }
    }

    val viewModelModule = module {
        viewModel { MoviesViewModel(get()) }
        viewModel { TvShowsViewModel(get()) }
        viewModel { DetailViewModel(get()) }
        viewModel { MoviesFavViewModel(get()) }
        viewModel { TvShowsViewModel(get()) }

    }
