package com.capstone.core.data.api


import com.capstone.core.BuildConfig
import com.capstone.core.data.source.remote.response.ResponseDetailMovie
import com.capstone.core.data.source.remote.response.ResponseDetailTv
import com.randhypi.mymovie.data.source.response.ResponseMovies
import com.randhypi.mymovie.data.source.response.ResponseTvShows
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("3/movie/top_rated")
    fun getMovies(@Query("api_key") api: String = BuildConfig.MOVIE_KEY,
                  @Query("language") language: String = "en-US",
                  @Query("page") page: String = "1"
                  ): Flowable<ResponseMovies>

    @GET("3/tv/popular")
    fun getTv(@Query("api_key") api: String =  BuildConfig.MOVIE_KEY,
              @Query("language") language: String = "en-US",
              @Query("page") page: String = "1"
    ): Flowable<ResponseTvShows>

    @GET("3/movie/{movie_id}")
    fun getDetailMovies(@Path("movie_id") id: String,@Query("api_key") api: String =  BuildConfig.MOVIE_KEY,
                        @Query("language") language: String = "en-US"): Flowable<ResponseDetailMovie>


    @GET("3/tv/{tv_id}")
    fun getDetailTv(@Path("tv_id") id: String,@Query("api_key") api: String =  BuildConfig.MOVIE_KEY,
                    @Query("language") language: String = "en-US"
    ): Flowable<ResponseDetailTv>




}