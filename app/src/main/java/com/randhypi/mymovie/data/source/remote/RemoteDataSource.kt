package com.randhypi.mymovie.data.source.remote.response

import android.util.Log
import com.randhypi.mymovie.data.api.ApiConfig
import com.randhypi.mymovie.data.api.ApiServices
import com.randhypi.mymovie.data.source.response.ResponseMovies
import com.randhypi.mymovie.data.source.response.ResponseTvShows
import com.randhypi.mymovie.data.source.response.ResultsItem
import com.randhypi.mymovie.data.source.response.ResultsItemTv
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {

        val TAG = RemoteDataSource::class.java.simpleName

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getMovies(callback: LoadMoviesCallback){
        var service: ApiServices = ApiConfig.getApiService()
        val callMovies: Call<ResponseMovies> = service.getMovies()
        callMovies.enqueue(object : Callback<ResponseMovies> {
            override fun onResponse(
                call: Call<ResponseMovies>,
                response: Response<ResponseMovies>
            ) {
                try {
                    if (!response.isSuccessful) {
                        Log.d(TAG, response.code().toString())
                        return
                    }
                 response.body().let {
                      callback.onAllMoviesReceived(it?.results!!)
                    }
                } catch (e: Exception) {
                    Log.d(TAG, e.toString())
                }
            }
            override fun onFailure(call: Call<ResponseMovies>, t: Throwable) {
                Log.d(TAG,"$call\n $t}")
            }
        })
    }

    fun getDetailMovies(callback: LoadDetailMoviesCallback,id: String){
        var service: ApiServices = ApiConfig.getApiService()
        val callDetailMovie: Call<ResponseDetailMovie> = service.getDetailMovies(id = id)

        callDetailMovie.enqueue(object : Callback<ResponseDetailMovie> {
            override fun onResponse(
                call: Call<ResponseDetailMovie>,
                response: Response<ResponseDetailMovie>
            ) {
                if (!response.isSuccessful) {
                    Log.d(TAG, response.code().toString())
                    return
                }
                response.body().let {
                    callback.onMoviesReceived(it)
                }
            }
            override fun onFailure(call: Call<ResponseDetailMovie>, t: Throwable) {
                Log.d(TAG,"$call\n $t}")
            }
        })
    }

    fun getTv(callback: LoadTvCallback){
        var service: ApiServices = ApiConfig.getApiService()
        val callMovies: Call<ResponseTvShows> = service.getTv()

        callMovies.enqueue(object : Callback<ResponseTvShows> {
            override fun onResponse(
                call: Call<ResponseTvShows>,
                response: Response<ResponseTvShows>
            ) {
                if (!response.isSuccessful) {
                    Log.d(TAG, response.code().toString())
                    return
                }
                response.body().let {
                    callback.onAllTvReceived(it?.results!!)
                }
            }
            override fun onFailure(call: Call<ResponseTvShows>, t: Throwable) {
                Log.d(TAG,"$call\n $t}")
            }
        })
    }

    fun getDetailTv(callback: LoadDetailTvCallback, id: String){
        var service: ApiServices = ApiConfig.getApiService()
        val callDetailMovie: Call<ResponseDetailTv> = service.getDetailTv(id = id)

        callDetailMovie.enqueue(object : Callback<ResponseDetailTv> {
            override fun onResponse(
                call: Call<ResponseDetailTv>,
                response: Response<ResponseDetailTv>
            ) {
                if (!response.isSuccessful) {
                    Log.d(TAG, response.code().toString())
                    return
                }
                response.body().let {
                    callback.onTvReceived(it)
                }
            }
            override fun onFailure(call: Call<ResponseDetailTv>, t: Throwable) {
                Log.d(TAG,"$call\n $t}")
            }
        })
    }



    interface LoadMoviesCallback{
        fun onAllMoviesReceived(moviesRsponse: List<ResultsItem?>)
    }

    interface LoadTvCallback{
        fun onAllTvReceived(tvShowsResponse: List<ResultsItemTv?>)
    }

    interface LoadDetailMoviesCallback{
        fun onMoviesReceived(detailMovieResponse: ResponseDetailMovie?)
    }

    interface LoadDetailTvCallback{
        fun onTvReceived(detailTvResponse: ResponseDetailTv?)
    }
}