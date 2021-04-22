package com.randhypi.mymovie.data.movies

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiServices {

    @GET("top_rated")
    @Headers("Authorization: f29f6eb26d31bf9cec8ecdd5e011af6f")
    fun getMovies(): Call<ResponseMovies>

}