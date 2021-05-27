package com.randhypi.mymovie.data.source.remote.response

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.capstone.core.data.api.ApiConfig
import com.capstone.core.data.api.ApiServices
import com.capstone.core.data.source.remote.ApiResponse
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
    }

    fun getMovies(): LiveData<ApiResponse<List<ResultsItem>>> {
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

      //              EspressoIdlingResource.decrement()
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

    fun getTv(): LiveData<ApiResponse<List<ResultsItemTv>>> {
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

            }

            override fun onFailure(call: Call<ResponseTvShows>, t: Throwable) {
                Log.d(TAG, "$call\n $t}")
            }
        })
        return resultTvShows
    }
}