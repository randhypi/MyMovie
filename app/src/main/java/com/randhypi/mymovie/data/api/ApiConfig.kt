package com.randhypi.mymovie.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiConfig {
    companion object {
        fun getApiService(): ApiServices {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://api.alquran.cloud/v1/surah")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiServices::class.java)
        }
    }
}
