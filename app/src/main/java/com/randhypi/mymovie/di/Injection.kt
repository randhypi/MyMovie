package com.randhypi.mymovie.di

import android.content.Context
import com.randhypi.mymovie.data.MoviesRepository
import com.randhypi.mymovie.data.source.local.LocalDataSource
import com.randhypi.mymovie.data.source.local.room.MoviesDatabase
import com.randhypi.mymovie.data.source.remote.response.RemoteDataSource
import com.randhypi.mymovie.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MoviesRepository {

        val database = MoviesDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.moviesDao())
        val appExecutors = AppExecutors()


        return MoviesRepository.getInstance(remoteDataSource,localDataSource,appExecutors)
    }
}