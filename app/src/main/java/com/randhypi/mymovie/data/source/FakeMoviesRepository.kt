package com.randhypi.mymovie.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.randhypi.mymovie.data.Movies
import com.randhypi.mymovie.data.TvShows
import com.randhypi.mymovie.data.source.remote.response.RemoteDataSource
import com.randhypi.mymovie.data.source.remote.response.ResponseDetailMovie
import com.randhypi.mymovie.data.source.remote.response.ResponseDetailTv
import com.randhypi.mymovie.data.source.response.ResultsItem
import com.randhypi.mymovie.data.source.response.ResultsItemTv
import com.randhypi.mymovie.ui.detail.DetailFragment.Companion.TAG

class FakeMoviesRepository(private val remoteDataSource: RemoteDataSource) :
    MoviesDataSource {

    override fun getMovies(): LiveData<List<Movies>> {
        val listMovies = MutableLiveData<List<Movies>>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesRsponse: List<ResultsItem?>) {
                val moviesArrayList = ArrayList<Movies>()
                for (response in moviesRsponse) {
                    val id = response?.id.toString()
                    val original_language = response?.originalLanguage
                    val original_title = response?.originalTitle
                    val title = response?.title
                    val release_date = response?.releaseDate
                    val poster = "https://image.tmdb.org/t/p/original${response?.posterPath}"
                    val overview = response?.overview
                    val popularity = response?.popularity

                    var movie = Movies(
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
                listMovies.postValue(moviesArrayList)
            }
        })
        return listMovies
    }

    override fun getTvShows(): LiveData<List<TvShows>> {
        val listTv = MutableLiveData<List<TvShows>>()
        remoteDataSource.getTv(object : RemoteDataSource.LoadTvCallback {
            override fun onAllTvReceived(tvShowsResponse: List<ResultsItemTv?>) {
                val tvList = ArrayList<TvShows>()
                for (response in tvShowsResponse) {
                    val id = response?.id!!.toString()
                    val original_language = response?.originalLanguage!!
                    val original_title = response?.originalName!!
                    val title = response?.name!!
                    val release_date = response?.firstAirDate!!
                    val poster = "https://image.tmdb.org/t/p/original${response?.posterPath!!}"
                    val overview = response?.overview!!
                    val popularity = response?.voteAverage!!

                    var tv = TvShows(
                        id = id,
                        name = title,
                        original_name = original_title,
                        original_language = original_language,
                        overview = overview,
                        popularity = popularity,
                        poster = poster,
                        date = release_date
                    )
                    tvList.add(tv)
                }
                listTv.postValue(tvList)
            }
        })
        return listTv
    }

    override fun getDetailMovies(moviesId: String): LiveData<Movies> {
        val moviesResult = MutableLiveData<Movies>()

        remoteDataSource.getDetailMovies(
            id = moviesId,
            callback = object : RemoteDataSource.LoadDetailMoviesCallback {
                override fun onMoviesReceived(detailMovie: ResponseDetailMovie?) {
                    lateinit var movie: Movies

                    val response = detailMovie

                    val id = response?.id.toString()
                    val original_language = response?.originalLanguage
                    val original_title = response?.title
                    val title = response?.title
                    val release_date = response?.releaseDate
                    val poster = "https://image.tmdb.org/t/p/original${response?.posterPath}"
                    val overview = response?.overview
                    val popularity = response?.popularity

                    movie = Movies(
                        id,
                        original_language,
                        original_title,
                        title,
                        release_date,
                        poster,
                        overview,
                        popularity
                    )
                    moviesResult.postValue(movie)
                }
            })
        return moviesResult
    }


    override fun getDetailTvShows(id: String): LiveData<TvShows> {
        val tvResult = MutableLiveData<TvShows>()

        remoteDataSource.getDetailTv(id =id,callback = object: RemoteDataSource.LoadDetailTvCallback{
            override fun onTvReceived(detailTvResponse: ResponseDetailTv?) {
               lateinit var tvShows: TvShows
               val response = detailTvResponse

                val id = response?.id!!.toString()
                val original_language = response?.originalLanguage!!
                val original_title = response?.originalName!!
                val title = response?.name!!
                val release_date = response?.firstAirDate!!
                val poster = response.posterPath!!
                val overview = response.overview!!
                val popularity = response.popularity!!

                tvShows = TvShows(
                    id = id,
                    name = title,
                    original_name = original_title,
                    original_language = original_language,
                    overview = overview,
                    popularity = popularity,
                    poster = poster,
                    date = release_date
                )
                tvResult.postValue(tvShows)
            }
        })
        return tvResult
    }


}