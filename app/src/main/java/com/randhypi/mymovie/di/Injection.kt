package com.randhypi.mymovie.di

import android.content.Context
import com.randhypi.mymovie.data.source.MoviesRepository
import com.randhypi.mymovie.data.source.remote.response.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): MoviesRepository {

        val remoteDataSource = RemoteDataSource.getInstance()

        return MoviesRepository.getInstance(remoteDataSource)
    }
}