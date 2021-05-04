package com.randhypi.mymovie.data.source.remote.response

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.randhypi.mymovie.data.api.ApiConfig
import com.randhypi.mymovie.data.api.ApiServices
import com.randhypi.mymovie.data.source.remote.ApiResponse
import com.randhypi.mymovie.data.source.response.ResponseMovies
import com.randhypi.mymovie.data.source.response.ResponseTvShows
import com.randhypi.mymovie.data.source.response.ResultsItem
import com.randhypi.mymovie.data.source.response.ResultsItemTv
import com.randhypi.mymovie.utils.EspressoIdlingResource
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

    fun getMovies(): LiveData<ApiResponse<List<ResultsItem>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<ResultsItem>>>()
        val service: ApiServices = ApiConfig.getApiService()
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

                    response.body()?.let {
                        it.results?.let {
                            resultMovies.value = ApiResponse.success(it)
                        }
                    }

                    EspressoIdlingResource.decrement()
                } catch (e: Exception) {
                    Log.d(TAG, e.toString())
                }
            }

            override fun onFailure(call: Call<ResponseMovies>, t: Throwable) {
                Log.d(TAG, "$call\n $t}")
            }
        })
        return resultMovies
    }

    fun getDetailMovies(id: String): LiveData<ApiResponse<ResponseDetailMovie>> {
        EspressoIdlingResource.increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<ResponseDetailMovie>>()
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

                response.body()?.let {
                    resultDetailMovie.value = ApiResponse.success(it)
                }

                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ResponseDetailMovie>, t: Throwable) {
                Log.d(TAG, "$call\n $t}")
            }
        })
        return resultDetailMovie
    }

    fun getTv(): LiveData<ApiResponse<List<ResultsItemTv>>> {
        EspressoIdlingResource.increment()
        val resultTvShows = MutableLiveData<ApiResponse<List<ResultsItemTv>>>()
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
                response.body()?.results?.let {
                    resultTvShows.value = ApiResponse.success(it)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ResponseTvShows>, t: Throwable) {
                Log.d(TAG, "$call\n $t}")
            }
        })
        return resultTvShows
    }

    fun getDetailTv(id: String): LiveData<ApiResponse<ResponseDetailTv>> {
        EspressoIdlingResource.increment()
        val resultDetailTv = MutableLiveData<ApiResponse<ResponseDetailTv>>()
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
                response.body()?.let {
                    resultDetailTv.value = ApiResponse.success(it)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ResponseDetailTv>, t: Throwable) {
                Log.d(TAG, "$call\n $t}")
            }
        })
        return resultDetailTv
    }
}