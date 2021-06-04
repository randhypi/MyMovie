package com.randhypi.mymovie.data.source.remote.response

import android.annotation.SuppressLint
import android.util.Log
import com.capstone.core.data.api.ApiServices
import com.capstone.core.data.source.remote.ApiResponse
import com.randhypi.mymovie.data.source.response.ResultsItem
import com.randhypi.mymovie.data.source.response.ResultsItemTv
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource(private val apiServices: ApiServices) {

    companion object {
        val TAG = RemoteDataSource::class.java.simpleName
    }

    @SuppressLint("CheckResult")
    fun getMovies(): Flowable<ApiResponse<List<ResultsItem>>> {
        val resultMovies = PublishSubject.create<ApiResponse<List<ResultsItem>>>()

        val result = apiServices.getMovies()

        result
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.results
                resultMovies.onNext(if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                resultMovies.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })


        return resultMovies.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getTv(): Flowable<ApiResponse<List<ResultsItemTv>>> {
        val resultTv = PublishSubject.create<ApiResponse<List<ResultsItemTv>>>()

        val result = apiServices.getTv()

        result
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.results
                resultTv.onNext(if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                resultTv.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })


        return resultTv.toFlowable(BackpressureStrategy.BUFFER)
    }

}