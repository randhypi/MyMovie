package com.capstone.core.di

import androidx.room.Room
import com.capstone.core.data.MoviesRepository
import com.capstone.core.data.api.ApiServices
import com.capstone.core.data.source.local.LocalDataSource
import com.capstone.core.data.source.local.room.MoviesDatabase
import com.capstone.core.domain.repository.IMoviesRepository
import com.capstone.core.utils.AppExecutors
import com.randhypi.mymovie.data.source.remote.response.RemoteDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


    val databaseModule = module {
        factory { get<MoviesDatabase>().moviesDao() }
        single {
            Room.databaseBuilder(
                androidContext(),
                MoviesDatabase::class.java, "Movies.db"
            ).fallbackToDestructiveMigration().build()
        }
    }

    val networkModule = module {
        single {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
        }
        single {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

            retrofit.create(ApiServices::class.java)
        }
    }

    val repositoryModule = module {
        single { LocalDataSource(get()) }
        single { RemoteDataSource(get()) }
        factory { AppExecutors() }
        single<IMoviesRepository> { MoviesRepository(get(), get(), get()) }
    }
