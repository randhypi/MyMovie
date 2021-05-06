package com.randhypi.mymovie.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.randhypi.mymovie.data.MoviesDataSource
import com.randhypi.mymovie.data.NetworkBoundResource
import com.randhypi.mymovie.data.source.local.LocalDataSource
import com.randhypi.mymovie.data.source.local.entity.MoviesEntity
import com.randhypi.mymovie.data.source.local.entity.TvShowsEntity
import com.randhypi.mymovie.data.source.remote.ApiResponse
import com.randhypi.mymovie.data.source.remote.response.RemoteDataSource
import com.randhypi.mymovie.data.source.remote.response.ResponseDetailMovie
import com.randhypi.mymovie.data.source.remote.response.ResponseDetailTv
import com.randhypi.mymovie.data.source.response.ResponseMovies
import com.randhypi.mymovie.data.source.response.ResultsItem
import com.randhypi.mymovie.data.source.response.ResultsItemTv
import com.randhypi.mymovie.ui.detail.DetailFragment.Companion.TAG
import com.randhypi.mymovie.utils.AppExecutors
import com.randhypi.mymovie.vo.Resource

class FakeMoviesRepository  constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    MoviesDataSource {


    override fun getMovies(): LiveData<Resource<List<MoviesEntity>>> {
       return object : NetworkBoundResource<List<MoviesEntity>, List<ResultsItem>>(appExecutors){
           override fun loadFromDB(): LiveData<List<MoviesEntity>> =
               localDataSource.getAllMovies()


           override fun shouldFetch(data: List<MoviesEntity>?): Boolean =
               data == null || data.isEmpty()


           override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> =
               remoteDataSource.getMovies()


           override fun saveCallResult(data: List<ResultsItem>) {
               val moviesArrayList = ArrayList<MoviesEntity>()
               for (response in data) {
                   val id = response?.id.toString()
                   val original_language = response?.originalLanguage
                   val original_title = response?.originalTitle
                   val title = response?.title
                   val release_date = response?.releaseDate
                   val poster = "https://image.tmdb.org/t/p/original${response?.posterPath}"
                   val overview = response?.overview
                   val popularity = response?.popularity

                   var movie = MoviesEntity(
                       id,
                       original_language,
                       original_title,
                       title, release_date,
                       poster,
                       overview,
                       popularity
                   )

                   moviesArrayList.add(movie)
               }
               localDataSource.insertMovies(moviesArrayList)
           }

       }.asLiveData()
    }

    override fun getTvShows(): LiveData<Resource<List<TvShowsEntity>>> {
        return object : NetworkBoundResource<List<TvShowsEntity>,List<ResultsItemTv>>(appExecutors){
            override fun loadFromDB(): LiveData<List<TvShowsEntity>> =
                localDataSource.getAllTv()


            override fun shouldFetch(data: List<TvShowsEntity>?): Boolean =
            data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsItemTv>>> =
               remoteDataSource.getTv()


            override fun saveCallResult(data: List<ResultsItemTv>) {
                val tvList = ArrayList<TvShowsEntity>()
                for (response in data) {
                    val id = response?.id!!.toString()
                    val original_language = response?.originalLanguage!!
                    val original_title = response?.originalName!!
                    val title = response?.name!!
                    val release_date = response?.firstAirDate!!
                    val poster = "https://image.tmdb.org/t/p/original${response?.posterPath!!}"
                    val overview = response?.overview!!
                    val popularity = response?.voteAverage!!

                    var tv = TvShowsEntity(
                        id = id,
                        name = title,
                        originalName = original_title,
                        originalLanguage = original_language,
                        overview = overview,
                        popularity = popularity,
                        poster = poster,
                        date = release_date
                    )
                    tvList.add(tv)
                }
               localDataSource.insertTvs(tvList)
            }
        }.asLiveData()
    }

    override fun getDetailMovies(id: String): LiveData<MoviesEntity> =
        localDataSource.getDetailMovie(id)

    override fun getDetailTvShows(id: String): LiveData<TvShowsEntity> =
        localDataSource.getDetailTv(id)

    override fun getFavMovies(): LiveData<List<MoviesEntity>> =
      localDataSource.getFavMovie()

    override fun getFavTvShows(): LiveData<List<TvShowsEntity>> =
      localDataSource.getFavTv()

    override fun setFavMovie(movie: MoviesEntity) =
      appExecutors.diskIO().execute {  localDataSource.updateMovie(movie) }


    override fun setFavTvShow(tv: TvShowsEntity) =
     appExecutors.diskIO().execute {localDataSource.updateTv(tv)}



}